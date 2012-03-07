package com.sshannin.pp.server;

import java.io.IOException;

import com.sshannin.pp.shared.Constants;

public class DBToXML {

	private DBToXML() {
	}

	private static final String DUMP_SCRIPT_LOC = "psquare_data/MyToJITXML.py ";

	public static void begin() {
	}

	private static void dumpDBToXML() {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(
					DUMP_SCRIPT_LOC + Constants.XML_DUMP_FNAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (p != null) {
			while (true) {
				try {
					p.waitFor();
					break;
				} catch (InterruptedException e) {
					System.out.println("Trouble waiting");
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	static {
		Runnable r = new Runnable() {
			public void run() {
				while (true) {
					System.out.print("Dumping DB to xml...");
					dumpDBToXML();
					System.out.println("done.");
					try {
						Thread.sleep(Constants.MS_PER_DB_TO_XML_DUMP);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		new Thread(r).start();
	}

}
