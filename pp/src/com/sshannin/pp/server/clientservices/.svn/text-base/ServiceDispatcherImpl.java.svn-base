package com.sshannin.pp.server.clientservices;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sshannin.pp.client.stubs.ServiceDispatcher;
import com.sshannin.pp.server.DBToXML;
import com.sshannin.pp.server.PhotoSearchImpl;

public class ServiceDispatcherImpl extends RemoteServiceServlet implements
		ServiceDispatcher {

	private static final long serialVersionUID = 1L;

	public Integer startServices() {
		PhotoSearchImpl.begin();
		DBToXML.begin();
		return 1;
	}

}
