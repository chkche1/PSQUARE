package com.sshannin.pp.client.stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sshannin.pp.client.PrivacyType;

/**
 * The async counterpart of <code>DBRoutines</code>.
 */
public interface DBRoutinesAsync {
	void getFirstNames(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void getNameFromID(Integer input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void authenticate(String login, String password,
			AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void getInfo(Integer login, 
			AsyncCallback<String> callback) throws IllegalArgumentException;

	void getInstitutions(Integer uid, AsyncCallback<List<String>> asyncCallback)
			throws IllegalArgumentException;

	void getTypeAttributes(Integer uid,
			AsyncCallback<HashMap<String, String>> callback)
			throws IllegalArgumentException;

	void getFriend(int userid, AsyncCallback<ArrayList<Integer>> callback);

	void commentPhoto(int pid, int userid, int score,
			AsyncCallback<Void> callback);

	void commentPhoto(int pid, int userid, String comments,
			AsyncCallback<Void> callback);

	void postPhoto(int pid, int userid, String url, PrivacyType p,
			AsyncCallback<Void> callback);

	void setPhotoPrivacy(int pid, int custom_id, boolean isCircle,
			AsyncCallback<Void> callback);

	void friendRecommendation(int userid,
			AsyncCallback<List<Integer>> callback);

	void getPhotosByRelevanceCached(int user,
			AsyncCallback<List<Integer>> callback);

	void getFriends(int me, AsyncCallback<List<Integer>> callback);

	void getCirclesIHave(int userid, AsyncCallback<List<Integer>> callback);

	void getURL(int pid, AsyncCallback<String> callback);

	void getPID(AsyncCallback<Integer> callback);

	void getTagsForPics(java.util.Collection<Integer> pids,
			AsyncCallback<Map<Integer, Set<String>>> callback);
	
	void getRecentPics(int userid,AsyncCallback<Map<Integer, String>> callback);

		void register(String f_name, String l_name, String email, int year,
			String gender, String address, String pwd,
			AsyncCallback<Void> asyncCallback);

	void friending(int cid,int userid, AsyncCallback<Boolean> callback);

	void getCircleName(int cid, AsyncCallback<String> callback);
}
