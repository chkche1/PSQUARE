package com.sshannin.pp.server.clientservices;

import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sshannin.pp.client.stubs.PhotoSearch;
import com.sshannin.pp.server.PhotoSearchImpl;

public class PhotoSearchStub extends RemoteServiceServlet implements
		PhotoSearch {

	private static final long serialVersionUID = 1L;

	public PhotoSearchStub() {
	}

	public Map<Integer, String> lookupTag(String tag) {
		return PhotoSearchImpl.lookupTag(tag);
	}
}
