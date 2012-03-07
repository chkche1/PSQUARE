package com.sshannin.pp.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sshannin.pp.client.stubs.ChatLink;
import com.sshannin.pp.client.stubs.ChatLinkAsync;

public class ChatHandler {

	private static final ChatLinkAsync chatLink = GWT.create(ChatLink.class);
	
	private static int cur_msg = 0;
	
	private static final VerticalPanel flowDiv = new VerticalPanel();
	
	private static final ScrollPanel scroller = new ScrollPanel(flowDiv);
		
	private static final com.sshannin.pp.shared.Function errorcb = new com.sshannin.pp.shared.Function(){
		public void run(){
			addEntry("Trouble posting/pulling messages, please try again");
		}
	};

	private ChatHandler(){}
	
	public static void publishChat(String msg, final com.sshannin.pp.shared.Function errorcb) {
		AsyncCallback<Integer> cb = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				errorcb.run();
			}

			public void onSuccess(Integer result) {
				//no problem, we good
			}
		};
		final String str = msg.trim();
		if(!str.equals(""))
			chatLink.publish(str, cb);
	}
	
	private static void poll(){
		final AsyncCallback<List<String>> cb = new AsyncCallback<List<String>>() {
			
			public void onFailure(Throwable caught) {
				errorcb.run();
				//flow.setText("<i>Error connecting to chat service, sorry.</i>");
			}

			public void onSuccess(List<String> result) {
				cur_msg += result.size();
				for(String s : result)
					addEntry(s);
			}
		};
		//System.out.println("Rquesting from msg " + cur_msg);
		chatLink.getMessagesSince(cur_msg, 10, cb);
	}
	
	private static void registerWithHost(){
		chatLink.getLastMessageNumber(new AsyncCallback<Integer>(){
			@Override
			public void onFailure(Throwable caught) {
				errorcb.run();
				//System.out.println("ERROR:Starting from msg " + 0);
			}

			@Override
			public void onSuccess(Integer result) {
				cur_msg = result;
				//System.out.println("Starting from msg " + cur_msg);
				
				//create a timer to repeatedly poll
				com.google.gwt.user.client.Timer t = new com.google.gwt.user.client.Timer() {
					public void run() {
						poll();
					}
				};
				t.scheduleRepeating(com.sshannin.pp.shared.Constants.CHAT_POLL_MS);	
			}		
		});
	}

	public static void beginChatServices() {
		registerWithHost();
		scroller.setHeight("100%");
		
		final TextArea input = new TextArea();
		input.setWidth("100%");
		input.addStyleName("NonResizable");

		RootPanel.get("chatFlow").add(scroller);
		RootPanel.get("chatEnter").add(input);
		
		class SendChatHandler implements KeyUpHandler {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String msg = input.getText().trim();
					input.setText("");
					if(msg != "")	
						publishChat(msg, errorcb);
				}
			}
		};
		
		input.addKeyUpHandler(new SendChatHandler());
	}
	
	private static void addEntry(String msg){
			flowDiv.add(new HTML(msg + "<br/>"));
			//final TextBox t = new TextBox();
			//	t.setWidth("95%");
			//t.setText(msg + (i++));
			//flowDiv.add(t);
			scroller.scrollToBottom();
	}
}
