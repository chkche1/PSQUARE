package com.sshannin.pp.server.clientservices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.zorbaxquery.api.DocumentManager;
import org.zorbaxquery.api.InMemoryStore;
import org.zorbaxquery.api.Item;
import org.zorbaxquery.api.XQuery;
import org.zorbaxquery.api.XmlDataManager;
import org.zorbaxquery.api.Zorba;

import com.sshannin.pp.client.stubs.XQRoutines;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class XQRoutinesImpl extends RemoteServiceServlet implements XQRoutines {

	private int hops = 0; // how many hops away from the logged in user are you
	private int loggedin = -1; // The user that's logged in
	private ArrayList<ArrayList<Integer>> browsable = new ArrayList<ArrayList<Integer>>(); // Contains
																							// a
																							// list
																							// of
																							// friends
																							// and
																							// friends
																							// of
																							// friends
	ArrayList<Integer> friends = new ArrayList<Integer>();
	ArrayList<Integer> friends_of_friends = new ArrayList<Integer>();

	static {
		System.loadLibrary("zorba_api");
	}

	InMemoryStore store;
	Zorba zorba;

	DBRoutinesImpl dbr = new DBRoutinesImpl();

	public XQRoutinesImpl() {
		friends.add(-1);
		friends_of_friends.add(-1);
		browsable.add(friends);
		browsable.add(friends_of_friends);
		store = InMemoryStore.getInstance();
		zorba = Zorba.getInstance(store);
	}

	public static final String query = "for $x in doc('mydump.sql')/db/* where $x/id = %PERSON return <user> {$x/id}"
			+ "																								{$x/name}"
			+ "																									 {for $y in doc('mydump.sql')/db/user where $y/id = $x/circle/friend/fid or $y/id = $x/advisor or $y/id = $x/advises return <circle><name>Pals</name>"
			+ "																											<containsFriend>{$y/id}{$y/name}</containsFriend></circle>}"
			+ " 																									  </user>";
	// public static final String query =
	// "for $x in doc('out.xml')/db/* where $x/ID = %PERSON return $x";
	private int previousUser = -1;

	@Override
	public String getPersonAndNeighbors(String xml, int uid)
			throws IllegalArgumentException {


		if (loggedin == -1) {
			loggedin = uid;
			hops = 0;
		} else {
			if (dbr.areFriends(loggedin, uid) || dbr.areFriends(uid, loggedin)) {
				if (!browsable.get(0).contains(uid)) {
					browsable.get(0).add(uid);
				}
				hops = 1;
			} else {
				for (int e : browsable.get(0)) {
					if (dbr.areFriends(e, uid) || dbr.areFriends(uid, e)) {
						browsable.get(1).add(uid);
						hops = 2;
					}
					break;
				}
				if (!browsable.get(1).contains(uid)) {
					hops = 3;
				}
			}
		}
		//System.out.println("Hops: "+ hops);
		if (hops < 2) {
			previousUser = uid;
		} else {
			uid = previousUser;
		}

		XmlDataManager dm = zorba.getXmlDataManager();

		org.zorbaxquery.api.Iterator i = dm.parseXML(xml);
		i.open();
		Item doc = Item.createEmptyItem();
		i.next(doc);
		i.close();
		i.delete();

		DocumentManager docMgr = dm.getDocumentManager();
		docMgr.put("mydump.sql", doc);

		doc.delete();

		String expandedQuery = query.replace("%PERSON", String.valueOf(uid));

		XQuery xquery = zorba.compileQuery(expandedQuery);
		String ret = xquery.execute();

		//System.out.println(expandedQuery);

		//System.out.println(ret);

		docMgr.remove("mydump.sql");

	/*	if (loggedin == -1) {
			loggedin = uid;
			hops = 0;
		} else {
			if (dbr.areFriends(loggedin, uid) || dbr.areFriends(uid, loggedin)) {
				if (!browsable.get(0).contains(uid)) {
					browsable.get(0).add(uid);
				}
				hops = 1;
			} else {
				for (int e : browsable.get(0)) {
					if (dbr.areFriends(e, uid) || dbr.areFriends(uid, e)) {
						browsable.get(1).add(uid);
						hops = 2;
					}
					break;
				}
				if (!browsable.get(1).contains(uid)) {
					hops = 3;
				}
			}
		}*/

		return ret;
	}

	// Reads the xml file for parsing
	public String readXML() {
		String xml = "";
		String thisLine;
		try {
			BufferedReader br = new BufferedReader(new FileReader(com.sshannin.pp.shared.Constants.XML_DUMP_FNAME));
			while ((thisLine = br.readLine()) != null) {
				xml += thisLine;
			}
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		return xml;
	}
	
	public String resetLoggedIn(){
		loggedin = -1;
		return "";
	}

}
