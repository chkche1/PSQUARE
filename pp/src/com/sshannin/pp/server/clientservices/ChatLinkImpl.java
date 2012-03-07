package com.sshannin.pp.server.clientservices;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sshannin.pp.client.stubs.ChatLink;
import com.sshannin.pp.server.ChatService;

public class ChatLinkImpl extends RemoteServiceServlet implements ChatLink {

	private static final long serialVersionUID = 1L;

	public ChatLinkImpl() {
	}

	public List<String> getMessagesSince(int last, int howMany) {
		//System.out.println("requesting " + howMany + " msgs, starting from " + lastMsg);
		List<String> msgs = ChatService.getMessagesSince(last, howMany);
		//System.out.println("Got " + msgs.size() + " msgs, will resume from " + lastMsg);
		return msgs;
	}

	public Integer getLastMessageNumber() {
		return ChatService.getLastMessageNumber();
	}

	public Integer publish(String msg) {
		ChatService.publish(msg);
		return 1;
	}
}
