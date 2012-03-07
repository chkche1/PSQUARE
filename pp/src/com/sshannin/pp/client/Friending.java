package com.sshannin.pp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;

public class Friending implements ClickHandler{
	final DBRoutinesAsync db;
	final int userid;
	final int friendid;
	
	final DialogBox dialog=new DialogBox();
	final Button closeButton=new Button("Close");
	final Button submitButton=new Button("Submit");
	final ArrayList<CheckBox> circleCb=new ArrayList<CheckBox>();
	final Label errorl=new Label();
	
	public Friending(final DBRoutinesAsync dbRoutines, int uid,int fid){
		this.db=dbRoutines;
		this.userid=uid;
		this.friendid=fid;
		this.db.getNameFromID(fid, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				errorl.setText("Remote Procedure Call -- error");
				dialog.setHeight("200px");
				dialog.add(errorl);
				dialog.add(closeButton);
				dialog.center();				
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				dialog.setText("Add "+result+" as friend");
			}});
		dialog.setText("Friend "+fid);
		closeButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dialog.hide();
			}});
		
		submitButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				for(int i=0;i<circleCb.size();i++){
					if(circleCb.get(i).isChecked()){
						int cid=Integer.parseInt(circleCb.get(i).getName());
						db.friending(cid, friendid, new AsyncCallback<Boolean>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								errorl.setText("Remote Procedure Call -- error");
								dialog.setHeight("200px");
								dialog.add(errorl);
								dialog.add(closeButton);
								dialog.center();
							}

							@Override
							public void onSuccess(Boolean result) {
								// TODO Auto-generated method stub
								if(!result){
									errorl.setText("Remote Procedure Call -- error");
									dialog.setHeight("200px");
									dialog.add(errorl);
									dialog.add(closeButton);
									dialog.center();									
								}else{
									dialog.setText("Done!");
									dialog.clear();
									dialog.add(closeButton);
									dialog.show();
								}
							}});
					}
				}
			}});
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		popup();
	}
	
	public void popup(){

		db.getCirclesIHave(userid, new AsyncCallback<List<Integer>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				errorl.setText("Remote Procedure Call -- error");
				dialog.setHeight("200px");
				dialog.add(errorl);
				dialog.add(closeButton);
				dialog.center();
			}

			@Override
			public void onSuccess(List<Integer> result) {
				// TODO Auto-generated method stub
				if(result.size()==0){
					errorl.setText("You dont have circle");
					dialog.setHeight("200px");
					dialog.add(errorl);
					dialog.add(closeButton);
					dialog.center();
				}else{
					VerticalPanel vp=new VerticalPanel();
					vp.add(new Label("Choose a circle:"));
					for(Integer i:result){
						final CheckBox cb=new CheckBox();
						circleCb.add(cb);
						cb.setName(i+"");
						db.getCircleName(i, new AsyncCallback<String>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								errorl.setText("Remote Procedure Call -- error");
								dialog.setHeight("200px");
								dialog.add(errorl);
								dialog.add(closeButton);
								dialog.center();
							}

							@Override
							public void onSuccess(String result) {
								// TODO Auto-generated method stub
								cb.setText(result);
							}});
						vp.add(cb);
					}
					HorizontalPanel hp=new HorizontalPanel();
					hp.add(submitButton);
					hp.add(closeButton);
					vp.add(hp);
					dialog.setWidget(vp);
					dialog.center();
				}
			}});
	}

	
}
