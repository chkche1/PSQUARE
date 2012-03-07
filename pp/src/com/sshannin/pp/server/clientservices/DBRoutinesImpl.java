package com.sshannin.pp.server.clientservices;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.sshannin.pp.client.PrivacyType;
import com.sshannin.pp.client.stubs.DBRoutines;
import com.sshannin.pp.server.DBQCache;
import com.sshannin.pp.shared.Constants;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DBRoutinesImpl extends RemoteServiceServlet implements DBRoutines {

	private java.sql.Connection connection;

	public DBRoutinesImpl() {
		final String serverName = "fling.seas.upenn.edu";
		final String database = "sshannin";
		final String user = "sshannin";
		final String passwd = "lmaonaise";
		final String url = "jdbc:mysql://" + serverName + "/" + database;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			System.out.println("ERROR CONNECTING TO DB");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find driver class");
			e.printStackTrace();
		}
	}

	public String getFirstNames(String input) throws IllegalArgumentException {
		StringBuffer ret = new StringBuffer();
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT first_name from Users;");

			java.sql.ResultSet rs = st.getResultSet();
			while (rs.next())
				ret.append(rs.getString(1));
		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return ret.toString();
	}
	
	public String getNameFromID(Integer id) throws IllegalArgumentException {
		StringBuffer ret = new StringBuffer();
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT Users.first_name from Users where Users.userid = '" + id + "'");

			java.sql.ResultSet rs = st.getResultSet();
			while (rs.next())
				ret.append(rs.getString(1));
		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}
		//System.out.println("Hello There: " + ret.toString());
		return ret.toString();
	}

	@Override
	public String authenticate(String login, String password)
			throws IllegalArgumentException {

		StringBuffer sb = new StringBuffer();
		int uid = -1;

		// Query basic Users table info
		try {
			java.sql.Statement st = connection.createStatement();
			// st.execute("select * from Users limit 10;");
			st.execute("SELECT * FROM Users WHERE " + "Users.email='" + login
					+ "' AND Users.password='" + password + "'");
			java.sql.ResultSet rs = st.getResultSet();

			while (rs.next()) { // should only be one entry in case of success
				uid = Integer.valueOf(rs.getString(1));
				sb.append(rs.getString(1) + "#" + rs.getString(2) + "#"
						+ rs.getString(3) + "#" + rs.getString(4) + "#"
						+ rs.getString(5) + "#" + rs.getString(6) + "#"
						+ rs.getString(7));
			}

		} catch (SQLException e) {
			System.out.println("Login SQL failed");
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	@Override
	public String getInfo(Integer login)
			throws IllegalArgumentException {

		StringBuffer sb = new StringBuffer();
		int uid = -1;

		// Query basic Users table info
		try {
			java.sql.Statement st = connection.createStatement();
			// st.execute("select * from Users limit 10;");
			st.execute("SELECT * FROM Users WHERE " + "Users.userid ='" + login + "'");
			java.sql.ResultSet rs = st.getResultSet();

			while (rs.next()) { // should only be one entry in case of success
				uid = Integer.valueOf(rs.getString(1));
				sb.append(rs.getString(1) + "#" + rs.getString(2) + "#"
						+ rs.getString(3) + "#" + rs.getString(4) + "#"
						+ rs.getString(5) + "#" + rs.getString(6) + "#"
						+ rs.getString(7));
			}

		} catch (SQLException e) {
			System.out.println("Login SQL failed");
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public List<String> getInstitutions(Integer uid)
			throws IllegalArgumentException {
		LinkedList<String> li = new LinkedList<String>();

		// Get Academic Inst.
		try {
			java.sql.Statement st2 = connection.createStatement();
			st2.execute("SELECT ai.institution FROM AcademicInstitution ai WHERE ai.userid="
					+ uid);
			java.sql.ResultSet rs = st2.getResultSet();

			while (rs.next()) {
				li.add(rs.getString("institution"));
			}

		} catch (SQLException e) {
			System.out.println("Login SQL failed");
			e.printStackTrace();
		}

		return li;
	}

	@Override
	public HashMap<String, String> getTypeAttributes(Integer uid)
			throws IllegalArgumentException {
		HashMap<String, String> map = new HashMap<String, String>();
		int advisorID = -1;
		boolean done = false;

		// Identify if is a student
		try {
			java.sql.Statement st3 = connection.createStatement();
			st3.execute("SELECT * FROM Student s WHERE s.userid=" + uid);
			java.sql.ResultSet rs = st3.getResultSet();

			while (rs.next()) {
				done = true;
				// there should only be one entry
				advisorID = rs.getInt("advisorid");
				map.put("Type", "Student");
				map.put("Year", "" + rs.getInt("year"));
			}

			// usr is a student get exact advisor name and roll
			if (done) {
				try {
					// System.out.println("within 2nd query");
					java.sql.Statement statement = connection.createStatement();
					statement
							.execute("SELECT first_name, last_name FROM Users WHERE Users.userid="
									+ advisorID);
					java.sql.ResultSet set = statement.getResultSet();
					while (set.next()) {
						map.put("Advisor", set.getString("first_name") + " "
								+ set.getString("last_name"));
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Identify if is a professor and set type to 2
		try {
			java.sql.Statement st4 = connection.createStatement();
			st4.execute("SELECT * FROM Professor WHERE Professor.userid=" + uid);
			java.sql.ResultSet set = st4.getResultSet();
			while (set.next()) {
				done = true;
				map.put("Type", "Professor");
				map.put("Research Area", set.getString("research_area"));
				map.put("Room ID", set.getString("room_id"));
			}

			// usr is a professor so get a list of advisees and roll
			if (done) {
				try {
					java.sql.Statement t = connection.createStatement();
					t.execute("SELECT u.first_name, u.last_name FROM Users u, Student s WHERE s.advisorid="
							+ uid + " AND u.userid=s.userid");
					java.sql.ResultSet s = t.getResultSet();
					int cnt = 0;
					while (s.next()) {
						map.put("" + cnt,
								s.getString("first_name") + " "
										+ s.getString("last_name"));
						cnt++;
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// The user is the primitive user type... declare type Student and
		// return...
		map.put("Type", "User");

		// TODO Auto-generated method stub
		return map;
	}

	// ================================================================================

	public ArrayList<Integer> getFriend(int userid)
			throws IllegalArgumentException {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Statement stmt = null;
		String query = "select F.friendid, F.cid, C.name from Circle C, FriendRelation F"
				+ " where C.userid=" + userid + " and C.cid=F.cid";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int friendid = rs.getInt("friendid");
				int circleid = rs.getInt("cid");
				String circlename = rs.getString("name");
				ret.add(friendid);
				// System.out.println("Friend "+friendid+" in circle "+
				// circleid+ " "+ circlename);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ret;
	}

	public void commentPhoto(int pid, int userid, int score)
			throws IllegalArgumentException {
		Statement stmt = null;
		String query = "insert into Rating (pid, userid,score) values (" + pid
				+ "," + userid + "," + score + ");";
		//System.out.println(query);
		try {
			stmt = connection.createStatement();
			int re = stmt.executeUpdate(query);
			// System.out.println("Friend "+friendid+" in circle "+ circleid+
			// " "+ circlename);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void commentPhoto(int pid, int userid, String comments)
			throws IllegalArgumentException {
		Statement stmt = null;
		String query = "insert into Tag (pid, userid,comments) values (" + pid
				+ "," + userid + ",'" + comments + "')";
		try {
			stmt = connection.createStatement();
			int re = stmt.executeUpdate(query);
			// System.out.println("Friend "+friendid+" in circle "+ circleid+
			// " "+ circlename);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void postPhoto(int pid, int userid, String url, PrivacyType p)
			throws IllegalArgumentException {
		Statement stmt = null;
		String query = "insert into Photo (pid, userid, url, privacy) values ("
				+ pid + "," + userid + ",'" + url + "'" + ",'" + p.toString()
				+ "')";
		//System.out.println(query);
		try {
			stmt = connection.createStatement();
			int re = stmt.executeUpdate(query);
			// System.out.println("Friend "+friendid+" in circle "+ circleid+
			// " "+ circlename);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void setPhotoPrivacy(int pid, int custom_id, boolean isCircle)
			throws IllegalArgumentException {
		Statement stmt = null;
		String query = new String();
		if (isCircle)
			query = "insert into CirclePrivacy (pid, cid) values (" + pid + ","
					+ custom_id + ");";
		else
			query = "insert into UserPrivacy (pid,userid) values (" + pid + ","
					+ custom_id + ");";
		try {
			stmt = connection.createStatement();
			int re = stmt.executeUpdate(query);
			// System.out.println("Friend "+friendid+" in circle "+ circleid+
			// " "+ circlename);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public List<Integer> friendRecommendation(int userid)
			throws IllegalArgumentException {
		try {
			ArrayList<Integer> list1 = this.friendRecommendationByPhoto(userid);
			List<Integer> list2 = this.getRecommendationsByFriends(userid);
			List<Integer> result = new LinkedList<Integer>(list2);
			for(Integer i : list1)
				if(!result.contains(i))
						result.add(i);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<Integer> friendRecommendationByPhoto(int userid)
			throws SQLException {
		Statement stmt;
		ArrayList<Integer> result = new ArrayList<Integer>();
		String query = "select R.userid, R.score from Rating R,"
				+ "( select pid as same_photo, userid from Rating where userid = "
				+ userid
				+ ") Common where R.pid=Common.same_photo and NOT R.userid=Common.userid";
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			int uid = rs.getInt("userid");
			result.add(uid);
		}
		return result;
	}

	@Override
	public List<Integer> getFriends(int me) {
		List<Integer> ret = new LinkedList<Integer>();
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT DISTINCT FR.friendid "
					+ "FROM Users U, Circle C, FriendRelation FR "
					+ "WHERE C.userid=U.userid AND FR.cid=C.cid and "
					+ "U.userid=" + me + ";");

			java.sql.ResultSet rs = st.getResultSet();
			while (rs.next())
				ret.add(rs.getInt(1));

			java.sql.Statement st1 = connection.createStatement();

			st1.execute("SELECT DISTINCT S.advisorid "
					+ "FROM Users U, Student S "
					+ "WHERE U.userid=S.userid and "
					+ "U.userid=" + me + ";");

			java.sql.ResultSet rs1 = st1.getResultSet();
			while (rs1.next()){
				//System.out.println("Hello There!!!" + rs1.getInt(1));
				ret.add(rs1.getInt(1));
			}
			
			java.sql.Statement st2 = connection.createStatement();

			st2.execute("SELECT DISTINCT S.userid "
					+ "FROM Student S "
					+ "WHERE S.advisorid=" + me + ";");

			java.sql.ResultSet rs2 = st2.getResultSet();
			while (rs2.next()){
				//System.out.println("Hello There!!!" + rs1.getInt(1));
				ret.add(rs2.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return ret;
	}

	public List<Integer> getCirclesImIn(int me) {
		List<Integer> ret = new LinkedList<Integer>();
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT cid " + "FROM FriendRelation "
					+ "WHERE friendid=" + me + ";");

			java.sql.ResultSet rs = st.getResultSet();
			while (rs.next())
				ret.add(rs.getInt(1));
		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return ret;
	}
	
	private static final String FOFQ = "SELECT FRHis.friendid " +
										"FROM Circle CMine, " +
											  "FriendRelation FRMine, " +
											  "Circle CHis, " +
											  "FriendRelation FRHis " +
										"WHERE CMine.userid=%ME AND " +
											  "CMine.cid=FRMine.cid AND " +
											  "FRMine.friendid=CHis.userid AND " +
											  "FRHis.cid=CHis.cid AND " +
											  "FRHis.friendid!=%ME;";

	public Collection<Integer> getFriendsOfFriends(int me) {
		Collection<Integer> ret = new TreeSet<Integer>();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(FOFQ.replaceAll("%ME", "" + me));
			while (rs.next())
				ret.add(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ret;
	}

	private static final String MutualFriendQ = "SELECT FRMine.friendid " +
												"FROM Circle CMine, " +
												     "FriendRelation FRMine, " +
												     "Circle CHis, " +
												     "FriendRelation FRHis " +
												"WHERE CMine.userid=%ME AND " +
													  "CMine.cid=FRMine.cid AND " +
													  "CHis.userid=%HIM AND " +
													  "CHis.cid=FRHis.cid AND " +
													  "FRMine.friendid=FRHis.friendid;";
	public Collection<Integer> getMutualFriends(int me, int him) {
		Collection<Integer> ret = new TreeSet<Integer>();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(MutualFriendQ.
											replaceAll("%ME", "" + me).
											replaceAll("%HIM", "" + him));
			while (rs.next())
				ret.add(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public boolean areFriends(int me, int him) {
		return getFriend(me).contains(him);
	}

	
	public List<Integer> getRecommendationsByFriends(final int me) {
		List<Integer> mine = getFriends(me);
		Collection<Integer> potentials = getFriendsOfFriends(me);

		potentials.removeAll(mine);
		potentials.remove((Integer) me);

		class Pair implements Comparable<Pair> {
			int id, count;

			Pair(int _id, int _count) {
				id = _id;
				count = _count;
			}

			public int compareTo(Pair that) {
				if (this.count == that.count)
					return this.id < that.id ? 1 : -1; // arbitrary for total
														// ordering
				if (this.count > that.count)
					return -1;
				return 1;
			}

			public String toString() {
				return "User " + me + " has " + count + " mutual friends with "
						+ id;
			}

			public boolean equals(Object that) {
				return ((Pair) that).id == this.id;
			}

		}
		;
		TreeSet<Pair> counts = new TreeSet<Pair>();

		for (Integer i : potentials)
			counts.add(new Pair(i, getMutualFriends(me, i).size()));

		List<Integer> ret = new ArrayList<Integer>(counts.size());
		for (Pair p : counts)
			ret.add(p.id);

		return ret;
	}

	private static final String visiblePhotoQ = 
			"SELECT P.pid, P.userid, P.privacy " +
			"From Photo P " +
			"WHERE P.userid=%USER OR P.privacy='ALL' OR " +
			"%USER in (SELECT UP.userid FROM UserPrivacy UP " +
					   "WHERE UP.pid=P.pid) OR " +
			"%USER in (SELECT FR.friendid " +
					   "FROM CirclePrivacy CP, Circle C, FriendRelation FR " +
					   "WHERE CP.pid=P.pid AND FR.cid=CP.cid)" +
					   ";";
	public LinkedList<Integer> visiblePhotos(int me) {
		LinkedList<Integer> ret = new LinkedList<Integer>();

		try {
			java.sql.Statement st = connection.createStatement();

			st.execute(visiblePhotoQ.replaceAll("%USER", ""+me));

			java.sql.ResultSet rs = st.getResultSet();
			while (rs.next())
				ret.add(rs.getInt(1));
			
		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return ret;
	}

	public int getOwnerByPhoto(int pid) {
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT userid " + "FROM Photo " + "WHERE pid=" + pid
					+ ";");

			java.sql.ResultSet rs = st.getResultSet();
			if (!rs.next()) // photo must exist
				assert (false);

			return rs.getInt(1);

		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return 0;
	}

	public double getAverageRating(int pid) {
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT AVG(score) " + "FROM Rating " + "WHERE pid="
					+ pid + ";");

			java.sql.ResultSet rs = st.getResultSet();
			if (!rs.next()) // photo must exist
				assert (false);

			return rs.getDouble(1);

		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return 0;
	}

	public double getAverageRatingByFriends(int pid, int me) {
		try {
			java.sql.Statement st = connection.createStatement();

			st.execute("SELECT AVG(R.score) "
					+ "FROM Rating R, Circle C, FriendRelation FR "
					+ "WHERE R.pid=" + pid + " AND FR.friendid=" + me
					+ " AND FR.cid=C.cid AND C.userid=R.userid;");

			java.sql.ResultSet rs = st.getResultSet();
			if (!rs.next()) // photo must exist
				assert (false);

			return rs.getDouble(1);

		} catch (SQLException e) {
			System.out.println("TROUBLES");

			e.printStackTrace();
		}

		return 0;
	}

	public double getRelevance(int pid, int user) {
		double score = 0;

		int owner = getOwnerByPhoto(pid);
		
		if (areFriends(owner, user))
			score += 4.51;

		score += getAverageRating(pid);
		
		score += (1.01*getAverageRatingByFriends(pid, user));
		

		return score;
	}

	public List<Integer> getPhotosByRelevanceCached(int user) {
		List<Integer> ret = DBQCache.lookup(user);
		if (ret != null)
			return ret;

		ret = getPhotosByRelevanceNonCached(user);
		DBQCache.cache(user, ret);
		return ret;
	}

	private List<Integer> getPhotosByRelevanceNonCached(int user) {
		List<Integer> photos = visiblePhotos(user);

		Map<Double, Set<Integer>> scores = new TreeMap<Double, Set<Integer>>();

		for (Integer p : photos) {
			double score = getRelevance(p, user);
			if (!scores.containsKey(score))
				scores.put(score, new HashSet<Integer>());

			scores.get(score).add(p);
		}
		LinkedList<Integer> ret = new LinkedList<Integer>();

		for (Entry<Double, Set<Integer>> e : scores.entrySet())
			for (Integer pid : e.getValue())
				ret.add(0, pid);
		return ret;
	}

	@Override
	public List<Integer> getCirclesIHave(int userid) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Statement stmt = null;
		String query = "select cid from Circle where userid=" + userid;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int uid;
			while (rs.next()) {
				uid = rs.getInt("cid");
				ret.add(uid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public String getURL(int pid) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		String query = "select url from Photo where pid=" + pid;
		try {
			stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String ret = rs.getString("url");
				return ret;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Integer getPID() {
		int id = -1;
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(pid)+1 FROM Photo");
			while (rs.next()) {
				id = rs.getInt("MAX(pid)+1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * Because of the way we insert photos, we assume greater pid -> new photo
	 */
	
	private static final String recentQ = 
			"SELECT P.pid, P.url " +
					   "FROM Photo P " +
					   "WHERE P.privacy='ALL' OR " +
					     "%USER in " +
					   		"(SELECT UP.userid FROM UserPrivacy UP WHERE UP.pid=P.pid) OR " +
					   "%USER in " +
					   		"(SELECT FR.friendid " +
					   			"FROM CirclePrivacy CP, FriendRelation FR " +
					   			"WHERE CP.pid=P.pid AND CP.cid=FR.cid) " +
					   "ORDER BY P.pid DESC " +
					   "LIMIT " + Constants.TRENDING_PHOTO_AMOUNT + ';';
	public Map<Integer, String> getRecentPics(int userid){
		Map<Integer, String> ret = new TreeMap<Integer, String>();
		try {
			final Statement st = connection.createStatement();
			final String q = recentQ.replaceAll("%USER", ""+userid);
			
			//System.out.println(q);
			ResultSet rs = st.executeQuery(q);
			while (rs.next()) {
				ret.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Map<Integer, Set<String>> getTagsForPics(java.util.Collection<Integer> pids){
		final java.util.Map<Integer, java.util.Set<String>> ret	= new HashMap<Integer, Set<String>>();
		String q = "SELECT pid, comments FROM Tag " +
				   "WHERE ";
		for (Integer pid : pids)
			q += "pid=" + pid + " OR ";
		q += "1=5;";//lazy way to end loop
				
				
				
		//System.out.println("Size: " + pids.size());
		//System.out.println("Query: '" + q+ "'");
				
				
		//add pids beforehand in case no tags returned
		for(Integer i : pids)
			ret.put(i, new TreeSet<String>());


		try {
			final Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				Integer pid = rs.getInt(1);
				String comment = rs.getString(2);
				//System.out.println("Processed comment " + comment + "for pid " + pid);
			
				ret.get(pid).add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

		@Override
	public void register(String f_name, String l_name, String email, int year,
			String gender, String address, String pwd)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		final String NULL = "null";
		final String STRMARK="'";
		final String COMMA = ",";
		Statement stmt;
		StringBuffer query = new StringBuffer(
				"insert into Users (first_name,last_name,dob,email,gender,address,password) values (");
		assert(f_name!=null);
		query.append(STRMARK).append(f_name).append(STRMARK);
		query.append(COMMA);
		
		assert(l_name!=null);
		query.append(STRMARK).append(l_name).append(STRMARK);
		query.append(COMMA);
		
		assert (year>1900 && year<2011);
		query.append(STRMARK).append(year).append("-01-01").append(STRMARK).append(COMMA);
		
		assert (email != null);
		query.append(STRMARK).append(email).append(STRMARK).append(COMMA);
		
		assert (gender.equals("F")||gender.equals("M"));
		query.append(STRMARK).append(gender).append(STRMARK).append(COMMA);
		
		assert (address!=null);
		query.append(STRMARK).append(address).append(STRMARK).append(COMMA);
		
		assert(pwd!=null && !(pwd.equals("")));
		query.append(STRMARK).append(pwd).append(STRMARK);
		
		query.append(")");
		//System.out.println(query);
		
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(query.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getCircleName(int cid) throws IllegalArgumentException{
		Statement stmt;
		String query="select name from Circle where cid="+cid;
		try {
			stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()){
				String name=rs.getString("name");
				return name;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public boolean friending(int cid, int userid)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Statement stmt;
		String query="insert into FriendRelation (friendid,cid) values ( "+userid+" , "+cid+" )";
		System.out.println(query);
		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
