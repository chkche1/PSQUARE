package com.sshannin.pp.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatService {

	private ChatService() {
	}

	public static int getLastMessageNumber() {
		return msgs.size();
	}

	public static void publish(String msg) {
		System.out.println("Msg published: '" + msg + "'");
		msgs.add(msg);
	}

	public static List<String> getMessagesSince(int last, int howMany) {
		List<String> ret = new LinkedList<String>();
		int length = msgs.size();
		for (int i = last; i < length; i++)
			ret.add(msgs.get(i));

		return ret;
	}

	private static final List<String> msgs = java.util.Collections
			.synchronizedList(new ArrayList<String>());

}
