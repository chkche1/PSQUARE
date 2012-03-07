package com.sshannin.pp.server.tests;

import java.util.Map;
import java.util.Map.Entry;

import com.sshannin.pp.server.PhotoSearchImpl;

public final class PhotoSearchTest {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				int i = 0;
				while (true) {
					i++;
					for (Entry<String, Map<Integer, String>> e : PhotoSearchImpl.getMap() 
							.entrySet()) {
						if (e.getKey().length() < 5)
							continue;
						System.out.print(e.getKey() + ": [");
						for (Entry<Integer, String> pics : e.getValue()
								.entrySet())
							System.out.print(pics.getKey() + ':'
									+ pics.getValue() + ", ");
						System.out.println("]");
					}
					System.out.println("Print " + (i));

					try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}

			}
		};

		new Thread(r).start();
	}
}
