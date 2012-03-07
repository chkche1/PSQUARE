package com.sshannin.pp.client.stubs;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("chatbox")
public interface ChatLink extends RemoteService {
	List<String> getMessagesSince(int last, int howmany) throws IllegalArgumentException;

	Integer getLastMessageNumber() throws IllegalArgumentException;

	Integer publish(String msg) throws IllegalArgumentException;
}
