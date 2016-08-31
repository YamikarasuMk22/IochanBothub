package org.soichiro.charactorbot.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadBotStatsFile {

	public static String ReadBotStats(File filename) {
		String message = "\n";

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));

			while (br.ready()) {

				String readed = br.readLine();

				if (readed.matches(".*//.*") || readed.length() == 0) {
					continue;
				}

				readed = readed.replaceAll(" ", "");

				// Systemtxtで設定されている値の右辺と左辺を分ける
				String[] splittxtbyeq = readed.split("=", 0);

				// 左辺の各単語の頭文字をとり略称を作る
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

	public static String getStatsValueString(File filename, String statsname) {
		String stats = "no stats";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));

			while (br.ready()) {
				String readed = br.readLine();

				if (readed.matches(".*" + statsname + ".*")) {
					readed = readed.replaceAll(" ", "");

					// Systemtxtで設定されている値の右辺と左辺を分ける
					String[] splittxtbyeq = readed.split("=", 0);

					stats = splittxtbyeq[1];

					break;
				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stats;
	}

	public static int getStatsValueInteger(File filename, String statsname) {
		int stats = -1;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));

			while (br.ready()) {
				String readed = br.readLine();

				if (readed.matches(".*" + statsname + ".*")) {
					readed = readed.replaceAll(" ", "");

					// Systemtxtで設定されている値の右辺と左辺を分ける
					String[] splittxtbyeq = readed.split("=", 0);

					stats = Integer.parseInt(splittxtbyeq[1]);

					break;
				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stats;
	}

	public static boolean getStatsValueBoolean(File filename, String statsname) {
		boolean stats = false;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));

			while (br.ready()) {
				String readed = br.readLine();

				if (readed.matches(".*" + statsname + ".*")) {
					readed = readed.replaceAll(" ", "");

					// Systemtxtで設定されている値の右辺と左辺を分ける
					String[] splittxtbyeq = readed.split("=", 0);

					if(splittxtbyeq[1].equals("TRUE") || splittxtbyeq[1].equals("true")) {
						stats = true;
					} else {
						stats = false;
					}

					break;
				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stats;
	}
}
