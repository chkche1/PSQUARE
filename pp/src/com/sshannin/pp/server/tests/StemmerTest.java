package com.sshannin.pp.server.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sshannin.pp.server.Stemmer;

public class StemmerTest {

	/**
	 * Test program for demonstrating the Stemmer. It reads text from a a list
	 * of files, stems each word, and writes the result to standard output. Note
	 * that the word stemmed is expected to be in lower case: forcing lower case
	 * must be done outside the Stemmer class. Usage: Stemmer file-name
	 * file-name ...
	 */
	public static void main(String[] args) {
		args = new String[] { "lol" };
		char[] w = new char[501];
		Stemmer s = new Stemmer();
		for (int i = 0; i < args.length; i++)
			try {
				FileInputStream in = new FileInputStream(args[i]);

				try {
					while (true)

					{
						int ch = in.read();
						if (Character.isLetter((char) ch)) {
							int j = 0;
							while (true) {
								ch = Character.toLowerCase((char) ch);
								w[j] = (char) ch;
								if (j < 500)
									j++;
								ch = in.read();
								if (!Character.isLetter((char) ch)) {
									/* to test add(char ch) */
									for (int c = 0; c < j; c++)
										s.add(w[c]);

									/* or, to test add(char[] w, int j) */
									/* s.add(w, j); */

									s.stem();
									{
										String u;

										/* and now, to test toString() : */
										u = s.toString();

										/*
										 * to test getResultBuffer(),
										 * getResultLength() :
										 */
										/*
										 * u = new String(s.getResultBuffer(),
										 * 0, s.getResultLength());
										 */

										System.out.print(u);
									}
									break;
								}
							}
						}
						if (ch < 0)
							break;
						System.out.print((char) ch);
					}
				} catch (IOException e) {
					System.out.println("error reading " + args[i]);
					break;
				}
			} catch (FileNotFoundException e) {
				System.out.println("file " + args[i] + " not found");
				break;
			}
	}
}
