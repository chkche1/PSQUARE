package com.sshannin.pp.client.stubs;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>PhotoSearch</code>.
 */
public interface ChatLinkAsync {
	void getMessagesSince(int last, int howMany, AsyncCallback<List<String>> callback)
			throws IllegalArgumentException;

	void getLastMessageNumber(AsyncCallback<Integer> callback)
			throws IllegalArgumentException;

	void publish(String msg, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
}
