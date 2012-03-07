package com.sshannin.pp.server;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.sshannin.pp.server.clientservices.DBRoutinesImpl;
import com.sshannin.pp.shared.Constants;

public final class PhotoSearchImpl {

	private PhotoSearchImpl() {
	}

	public static Map<Integer, String> lookupTag(String tag) {
		final Stemmer s = new Stemmer();
		
		final char[] chars = tag.toCharArray();
		s.add(chars, tag.length());
		s.stem();
		tag = s.toString();
		
		Map<Integer, String> res = index.get(tag);
		if ( res == null)
			return new HashMap<Integer, String>();
				
		return new HashMap<Integer, String>(res);
	}

	private static final Map<String, Map<Integer, String>> index = new ConcurrentHashMap<String, Map<Integer, String>>();

	public static void begin() {
	}
	
	//just for testing really, shouldn't use this
	public static  Map<String, Map<Integer, String>> getMap(){
		return index;
	}

	private static boolean firstRun = true;
	private static final Stemmer stemmer = new Stemmer();

	static {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.print("Rebuilding Inverted Index...");
					populateIndex();
					System.out.println("done.");
					firstRun = false;

					try {
						Thread.sleep(Constants.MS_PER_INVERTED_INDEX_REBUILD);
					} catch (InterruptedException e) {
						System.out
								.println("Trouble sleeping while waiting to rebuild index");
						e.printStackTrace();
					}
				}
			}

		};

		new Thread(r).start();
	}

	private static java.sql.Connection initConnection() {
		final String serverName = "fling.seas.upenn.edu";
		final String database = "sshannin";
		final String user = "sshannin";
		final String passwd = "lmaonaise";
		final String url = "jdbc:mysql://" + serverName + "/" + database;
		final java.sql.Connection cnxn;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnxn = DriverManager.getConnection(url, user, passwd);
			return cnxn;
		} catch (SQLException e) {
			System.out.println("ERROR CONNECTING TO DB");
			System.out.println(e.getMessage());
			e.printStackTrace();
			// System.exit(1);
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find driver class");
			e.printStackTrace();
			// System.exit(1);
		}

		return null;
	}

	private static void populateIndex() {
		final java.sql.Connection cnxn = initConnection();
		final DBRoutinesImpl db = new DBRoutinesImpl();

		try {
			java.sql.Statement st = cnxn.createStatement();

			st.execute("SELECT pid, comments " + "FROM Tag;");

			java.sql.ResultSet rs = st.getResultSet();
			while (rs.next()) {
				final Integer pid = rs.getInt(1);
				final String comment = rs.getString(2);
				final String[] words = comment.split(" ");
				for (String s : words) {
					char[] chars = s.toCharArray();
					stemmer.add(chars, s.length());
					stemmer.stem();
					s = stemmer.toString();
					if (!index.containsKey(s)) {
						if (!firstRun)
							System.out.println("Found new keyword: " + s);
						index.put(s, new ConcurrentHashMap<Integer, String>());
					}

					index.get(s).put(pid, db.getURL(pid));
				}
			}
		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		try {
			cnxn.close();
		} catch (SQLException e) {
			System.out.println("Can't close cnxn; don't really care");

			e.printStackTrace();
		}
	}
}
