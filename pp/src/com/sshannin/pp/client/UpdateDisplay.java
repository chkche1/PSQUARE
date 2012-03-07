package com.sshannin.pp.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sshannin.pp.client.stubs.DBRoutines;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;
import com.sshannin.pp.shared.Constants;

public class UpdateDisplay {
	final int uid;
	private final DBRoutinesAsync dbRoutines = GWT.create(DBRoutines.class);
/*	final int UPDATE_POST=3;
	final int UPDATE_TAG=2;
	final HorizontalPanel hp=new HorizontalPanel();
	final Grid g=new Grid(1,UPDATE_POST+UPDATE_TAG);
	final VerticalPanel postCell[]=new VerticalPanel[UPDATE_POST];
	final VerticalPanel tagCell[]=new VerticalPanel[UPDATE_TAG];*/
	
	final int UPDATE = Constants.TRENDING_PHOTO_AMOUNT;
	final HorizontalPanel hp=new HorizontalPanel();
	final Grid g=new Grid(1,UPDATE);

	final HorizontalPanel up[]=new HorizontalPanel[UPDATE];
	final DialogBox errorDialog=new DialogBox();
	final Button errorCloseButton=new Button();
		
	public UpdateDisplay(int userid){
		this.uid=userid;
		this.setPanel();
		//this.setTagUpdates();
	}
	
	private void setPanel(){
		//errorDialog.setText("I am here");
		//errorDialog.center();
/*		for(int i=0;i<UPDATE_POST;i++){
			g.setWidget(0, i, postCell[i]);
		}
		for(int i=0;i<UPDATE_TAG;i++){
			g.setWidget(0, UPDATE_POST+i, tagCell[i]);
		}*/
		
/*		for(int i=0;i<UPDATE;i++)
			g.setWidget(0, i, updateCell[i]);*/
		for(int i=0;i<UPDATE;i++){
			HorizontalPanel p=new HorizontalPanel();
			up[i]=p;
			hp.add(up[i]);
		}
		//hp.add(g);
		RootPanel.get("updateContainer").add(hp);
		
		errorCloseButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				errorDialog.hide();
			}});
		errorDialog.setWidget(errorCloseButton);
		this.setPostUpdates();
	}
	
	private void setPostUpdates(){
		//System.out.println("I am here getting updates");
		dbRoutines.getRecentPics(this.uid, new AsyncCallback<Map<Integer,String>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				errorDialog.setText("Remote Procedure Call -- error");
				errorDialog.center();
			}

			@Override
			public void onSuccess(Map<Integer, String> result) {
				// TODO Auto-generated method stub
				final Map<Integer,String> urlMap=result;
				List<Integer> pids=new ArrayList<Integer>();
				//Label l=new Label("Recent post:");
/*				for(Map.Entry<Integer, String> e : result.entrySet()){
					pids.add(e.getKey());
					Image img=new Image(e.getValue());
					img.setPixelSize(100, 100);
					//postCell[count].add(l);
					updateCell[count].add(img);
					count++;
					if(count>=UPDATE)
						break;
				}*/
				//System.out.println("I got "+result.size()+" updates");
				pids.addAll(result.keySet());
				dbRoutines.getTagsForPics(pids, new AsyncCallback<Map<Integer,Set<String>>>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						errorDialog.setText("Remote Procedure Call -- error");
						errorDialog.center();						
					}

					@Override
					public void onSuccess(Map<Integer, Set<String>> result) {
						// TODO Auto-generated method stub
						int count=0;
						for(final Map.Entry<Integer, Set<String>> e : result.entrySet()){
							Image img=new Image(urlMap.get(e.getKey()));
							//System.out.println("I got url: "+urlMap.get(e.getKey())+"with "+e.getValue().size()+" tags");
							img.setPixelSize(100, 100);
							img.addClickHandler(new PhotoRateWindow(dbRoutines, uid, 
												e.getKey(), urlMap.get(e.getKey())));
						
							up[count].add(img);
							int tagCount=0;
							VerticalPanel text=new VerticalPanel();
							for(String s : e.getValue()){
								
								Label l=new Label(s);
								text.add(l);
								tagCount++;
								if(tagCount>=3)
									break;
							}
							up[count].add(text);
							count++;
							if(count>=UPDATE)
								break;
						}
					}});
				
				
			}});
	}
	
	/*private void setTagUpdates(){
		dbRoutines.getRecentTags(uid, new AsyncCallback<Map<Integer,String>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				errorDialog.setText("Remote Procedure Call -- error");
				errorDialog.center();
			}

			@Override
			public void onSuccess(Map<Integer, String> result) {
				// TODO Auto-generated method stub
				int count=0;
				Label l=new Label("Recent tag:");
				for(Map.Entry<Integer, String> e : result.entrySet()){
					Image img=new Image(e.getValue());
					img.setPixelSize(100, 100);
					tagCell[count].add(l);
					tagCell[count].add(img);
					count++;
					if(count>=UPDATE_POST)
						break;
				}
			}});
	}*/
	
}
