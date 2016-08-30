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
		String txtline = null;
		String message = null;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(SYSTEM_TXT_FILE));

			while ((txtline = br.readLine()) != null) {

				if(txtline.matches(".*//.*") || txtline == null) {
					continue;
				}

				txtline = txtline.replaceAll(" ", "");

				//Systemtxtで設定されている値の右辺と左辺を分ける
				String[] splittxtbyeq = txtline.split("=", 0);

				//左辺の各単語の頭文字をとり略称を作る
				String[] splittxtbyub = splittxtbyeq[0].split("_", 0);
				String propertyname = null;

				for(int i=0; i<=splittxtbyub.length; i++){
					propertyname = propertyname + splittxtbyub[i].substring(0,0);
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
