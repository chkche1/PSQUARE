package com.sshannin.pp.client.stubs;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>PhotoSearch</code>.
 */
public interface PhotoSearchAsync {
	void lookupTag(String input, AsyncCallback<Map<Integer, String>> callback)
			throws IllegalArgumentException;
}
