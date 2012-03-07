package com.sshannin.pp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;

public class PhotoPosting extends Widget {
	DBRoutinesAsync db;
	int user_id;
	int pid;

	final String DEFAULT = "Input the url of the photo";
	final String TAG_DEFAULT = "Tag for the photo";
	final DialogBox dialogBox = new DialogBox();
	final Button uploadButton = new Button("Upload");
	final TextBox urlInput = new TextBox();
	final TextBox tagInput = new TextBox();

	public PhotoPosting(DBRoutinesAsync dbRoutines, int user_id) {
		this.db = dbRoutines;
		this.user_id = user_id;
	}

	public void post(int pid) {
		this.pid = pid;
		dialogBox.setText("Upload a Photo");
		dialogBox.setAnimationEnabled(true);
		dialogBox.setSize("200px", "200px");

		urlInput.setFocus(true);
		urlInput.setText(DEFAULT);
		tagInput.setText(TAG_DEFAULT);

		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dialogBox.hide();
			}

		});
		
		HorizontalPanel hp = new HorizontalPanel();
		VerticalPanel vp = new VerticalPanel();
		//hp.setSize("200px", "50px");
		//hp.setWidth("200px");
		
		
		vp.add(urlInput);
		vp.add(tagInput);
		hp.add(vp);
		hp.add(uploadButton);
		hp.add(close);
		
		dialogBox.setWidget(hp);
		dialogBox.center();
		//dialogBox.setSize("200px", "100px");

		UrlInputHandler handler = new UrlInputHandler();
		urlInput.addClickHandler(handler);
		urlInput.addKeyPressHandler(handler);
		tagInputHandler handle = new tagInputHandler();
		tagInput.addClickHandler(handle);
		tagInput.addKeyPressHandler(handle);
		
		uploadButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String url = urlInput.getText();
				String tag = tagInput.getText();
				dialogBox.hide();
				verifyPhoto(url, tag);
			}

		});

	}

	private void verifyPhoto(final String url, final String tag) {
		final DialogBox d = new DialogBox();
		d.setText("Verify the photo");
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		Image im = new Image(url);
		im.setPixelSize(200, 200);
		Label l = new Label("Is this the photo you want to upload?");
		Button yes = new Button("Yes");
		Button no = new Button("No");
		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				d.hide();
			}

		});
		vp.add(im);
		vp.add(l);
		hp.add(yes);
		hp.add(no);
		hp.add(close);
		vp.add(hp);
		d.add(vp);
		d.center();

		yes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				setPrivacy(url);
				setTag(tag);
				d.hide();
			}
		});

		no.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				d.hide();
				dialogBox.center();
			}

		});

	}

	private void setTag(final String tag){
		if (!tag.equals(""))
			db.commentPhoto(pid, user_id, tag, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					System.out.println("Fail to tag");
				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					System.out.println("Successfully tagged");
				}

			});
	
	}
	
	private void setPrivacy(final String url) {
		final DialogBox d = new DialogBox();
		d.setText("Set Visibility");
		HorizontalPanel hp = new HorizontalPanel();
		Button all = new Button("ALL");
		Button custom = new Button("CUSTOM");
		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				d.hide();
			}

		});
		hp.add(all);
		hp.add(custom);
		hp.add(close);
		d.add(hp);
		d.center();

		all.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				db.postPhoto(pid, user_id, url, PrivacyType.ALL,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Label l = new Label(
										"Remote Procedure Call - Failure");
								d.setWidget(l);
								d.center();
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								d.hide();
							}

						});
			}

		});

		custom.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				db.postPhoto(pid, user_id, url, PrivacyType.CUSTOM,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Label l = new Label(
										"Remote Procedure Call - Failure");
								d.setWidget(l);
								d.center();
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								d.hide();
								assignVisibility();
							}

						});

			}

		});
	}

	private void assignVisibility() {
		final DialogBox d = new DialogBox();
		d.setText("Assign Visibility");
		HorizontalPanel hp = new HorizontalPanel();
		final VerticalPanel user = new VerticalPanel();
		final VerticalPanel circle = new VerticalPanel();
		Button submit = new Button("Submit");
		Label userLabel = new Label("Your Friends");
		Label circleLabel = new Label("Your Circles");
		user.add(userLabel);
		circle.add(circleLabel);
		final ArrayList<CheckBox> userCb = new ArrayList<CheckBox>();
		final ArrayList<CheckBox> circleCb = new ArrayList<CheckBox>();
		db.getFriend(this.user_id, new AsyncCallback<ArrayList<Integer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Label l = new Label("Remote Procedure Call - Failure");
				d.setWidget(l);
				d.center();
			}

			@Override
			public void onSuccess(ArrayList<Integer> result) {
				// TODO Auto-generated method stub
				for (int i = 0; i < result.size(); i++) {
					CheckBox cb = new CheckBox();
					cb.setText(result.get(i) + "");
					cb.setValue(false);
					userCb.add(cb);
					user.add(cb);
				}

			}

		});

		AsyncCallback<List<Integer>> cb = new AsyncCallback<List<Integer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Label l = new Label("Remote Procedure Call - Failure");
				d.setWidget(l);
				d.center();
			}

			@Override
			public void onSuccess(List<Integer> result) {
				// TODO Auto-generated method stub
				for (int i = 0; i < result.size(); i++) {
					CheckBox cb = new CheckBox();
					cb.setText(result.get(i) + "");
					cb.setValue(false);
					circleCb.add(cb);
					circle.add(cb);
				}
			}
		};

		db.getCirclesIHave(this.user_id, cb);

		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				for (int i = 0; i < userCb.size(); i++) {
					if (userCb.get(i).getValue()) {
						db.setPhotoPrivacy(pid,
								Integer.parseInt(userCb.get(i).getText()),
								false, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										Label l = new Label(
												"Remote Procedure Call - Failure");
										d.setWidget(l);
										d.center();
									}

									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										d.hide();
										final DialogBox done = new DialogBox();
										done.setText("Done");
										Button close = new Button("Close");
										done.setWidget(close);
										close.addClickHandler(new ClickHandler() {

											@Override
											public void onClick(ClickEvent event) {
												// TODO Auto-generated method
												// stub
												done.hide();
											}

										});
									}

								});
					}
				}

				for (int i = 0; i < circleCb.size(); i++) {
					if (circleCb.get(i).getValue()) {
						db.setPhotoPrivacy(pid,
								Integer.parseInt(circleCb.get(i).getText()),
								true, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										Label l = new Label(
												"Remote Procedure Call - Failure");
										d.setWidget(l);
										d.center();
									}

									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										d.hide();
									}

								});
					}
				}
			}

		});

		hp.add(user);
		hp.add(circle);
		hp.add(submit);
		d.add(hp);
		d.center();

	}

	
	class tagInputHandler implements KeyPressHandler, ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			clear();
		}

		@Override
		public void onKeyPress(KeyPressEvent event) {
			// TODO Auto-generated method stub
			clear();
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {

			}
		}
		private void clear() {
			if (tagInput.getText().equals(TAG_DEFAULT))
				tagInput.setText("");
		}
		
	}
	
	class UrlInputHandler implements KeyPressHandler, ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			clear();
		}

		@Override
		public void onKeyPress(KeyPressEvent event) {
			// TODO Auto-generated method stub
			clear();
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {

			}
		}

		private void clear() {
			if (urlInput.getText().equals(DEFAULT))
				urlInput.setText("");
		}

	}

}
