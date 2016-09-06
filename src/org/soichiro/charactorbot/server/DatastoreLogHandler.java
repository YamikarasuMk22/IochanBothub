package org.soichiro.charactorbot.server;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import org.soichiro.charactorbot.client.CLogEntry;

/**
 * Log handler to store record to datastore.
 * If using this, use TwitterBotException.
 * @author soichiro
 *
 */
public class DatastoreLogHandler extends Handler {

	/** System I/O text file */
	private static final File SYSTEM_TXT_FILE = new File("datafile/systemtxt.txt");

	private static final SimpleFormatter simpleFormatter =  new SimpleFormatter();
	public static final int LIMIT_OF_LOG_ENTRY = 5;

	/* (non-Javadoc)
	 * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
	 */
	@Override
	public void publish(LogRecord record) {

		// Only WARNING is permitted.
		if(!record.getLevel().equals(Level.WARNING)) return;

		String logText = simpleFormatter.format(record);

		Throwable t = record.getThrown();
		if(t == null || !(t instanceof TwitterBotException)) return;
		String keyTwitterAccount = ((TwitterBotException)t).getKeyTwitterAccount();
		if(keyTwitterAccount == null || "".equals(keyTwitterAccount)) return;

		CLogEntry logEntry = new CLogEntry();
		logEntry.setTwitterAccount(keyTwitterAccount);
		logEntry.setLogType(record.getLevel().toString());
		logEntry.setLogText(logText);
		logEntry.setCreatedAt(new Date());

		Queue<CLogEntry> queue = LogEntryQueueCache.get(keyTwitterAccount);
		if(queue == null){
			queue = new ArrayDeque<CLogEntry>();
		}
		queue.add(logEntry);

		// get limit of log entry
		int limlog = -1;
		limlog = ReadBotStatsFile.getStatsValueInteger(SYSTEM_TXT_FILE, "LIMIT_OF_LOG_ENTRY");

		// �ی�
		if(limlog == -1) {
			limlog = LIMIT_OF_LOG_ENTRY;
		}

		int size = queue.size();
		if(size > limlog){
			int removeCount = size - limlog;
			for (int i = 0; i < removeCount; i++) {
				queue.remove();
			}
		}

		LogEntryQueueCache.put(keyTwitterAccount, queue);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Handler#flush()
	 */
	@Override
	public void flush() {
		// Do nothing
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Handler#close()
	 */
	@Override
	public void close() throws SecurityException {
		// Do nothing
	}
}
