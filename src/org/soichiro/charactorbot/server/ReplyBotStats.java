package org.soichiro.charactorbot.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReplyBotStats {
	/** System I/O text file */
	private static final File SYSTEM_TXT_FILE = new File("datafile/systemtxt.txt");
	/** Data I/O text file */
	private static final File DATA_TXT_FILE = new File("datafile/datatxt.txt");

	public static String ReplySystemproperty() {
		String message = "\n";

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(SYSTEM_TXT_FILE));

			while (br.ready()) {

				String readed = br.readLine();

				if (readed.matches(".*//.*") || readed.length() == 0) {
					continue;
				}

				readed = readed.replaceAll(" ", "");

				// Systemtxt�Őݒ肳��Ă���l�̉E�ӂƍ��ӂ𕪂���
				String[] splittxtbyeq = readed.split("=", 0);

				// ���ӂ̊e�P��̓��������Ƃ藪�̂����
				String[] splittxtbyub = splittxtbyeq[0].split("_", 0);
				String propertyname = "";

				for (int i = 0; i < splittxtbyub.length; i++) {
					propertyname = propertyname + splittxtbyub[i].substring(0, 1);
				}

				message = message + propertyname + "=" + splittxtbyeq[1] + "\n";
			}

			br.close();
		} catch (IOException e) {
			message = message + "ERROR:IOException";
			e.printStackTrace();
		}

		return message;
	}
}
