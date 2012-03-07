package com.sshannin.pp.client.stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sshannin.pp.client.PrivacyType;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("dbroutines")
public interface DBRoutines extends RemoteService {
	String getFirstNames(String name) throws IllegalArgumentException;

	String getNameFromID(Integer id) throws IllegalArgumentException;
	
	String authenticate(String login, String password)
			throws IllegalArgumentException;
	
	String getInfo(Integer login)
			throws IllegalArgumentException;

	List<String> getInstitutions(Integer uid) throws IllegalArgumentException;

	HashMap<String, String> getTypeAttributes(Integer uid)
			throws IllegalArgumentException;

	/**
	 * 
	 * @param userid
	 * @return a list of friend id
	 * @throws IllegalArgumentException
	 */
	ArrayList<Integer> getFriend(int userid) throws IllegalArgumentException;

	void commentPhoto(int pid, int userid, int score)
			throws IllegalArgumentException;

	void commentPhoto(int pid, int userid, String comments)
			throws IllegalArgumentException;

	void postPhoto(int pid, int userid, String url, PrivacyType p)
			throws IllegalArgumentException;

	void setPhotoPrivacy(int pid, int custom_id, boolean isCircle)
			throws IllegalArgumentException;

	List<Integer> friendRecommendation(int userid)
			throws IllegalArgumentException;

	List<Integer> getFriends(int me);

	List<Integer> getPhotosByRelevanceCached(int user)
			throws IllegalArgumentException;

	List<Integer> getCirclesIHave(int userid);

	String getURL(int pid) throws IllegalArgumentException;

	Integer getPID() throws IllegalArgumentException;
	
	Map<Integer, String> getRecentPics(int userid) throws IllegalArgumentException;
	
	public Map<Integer, Set<String>> getTagsForPics(java.util.Collection<Integer> pids) throws IllegalArgumentException;

	void register(String f_name, String l_name, String email, int year,
			String gender, String address, String pwd);

	boolean friending(int cid,int userid) throws IllegalArgumentException;

	String getCircleName(int cid) throws IllegalArgumentException;
}
