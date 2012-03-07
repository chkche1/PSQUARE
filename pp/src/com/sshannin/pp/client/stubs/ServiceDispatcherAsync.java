package com.sshannin.pp.client.stubs;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ServiceDispatcher</code>.
 */
public interface ServiceDispatcherAsync {
	void startServices(AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
}
