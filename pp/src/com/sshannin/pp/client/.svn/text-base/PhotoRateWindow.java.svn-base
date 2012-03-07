package com.sshannin.pp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;

public class PhotoRateWindow implements ClickHandler{
	final DBRoutinesAsync db;
	final int pid;
	final String url;
	final int uid;
	/**
	 * 
	 * @param db
	 * @param rater_id
	 *            : the user_id of who rates this photo, not owner
	 *  @param pid the pid of the photo being rated
	 *  @param url the url of the photo being rated (to display in the popup
	 */
	public PhotoRateWindow(DBRoutinesAsync db, final int rater_id, final int pid, final String url) {
		this.db = db;
		this.pid=pid;
		this.url=url;
		this.uid = rater_id;
	}

	@Override
	public void onClick(ClickEvent event) {
		rate();
	}
	
	private void rate() {
		final DialogBox d = new DialogBox();
		d.setText("Rate/Tag Photo");
		final VerticalPanel vp = new VerticalPanel();

		Image img = new Image(url);
		img.setPixelSize(200, 200);
		vp.add(img);

		HorizontalPanel hp = new HorizontalPanel();
		final TextBox comments = new TextBox();
		final ListBox score = new ListBox();
		for (int i = 1; i <= 5; i++)
			score.addItem(i + "");
		score.setVisibleItemCount(1);
		hp.add(comments);
		hp.add(score);
		vp.add(hp);

		Button submit = new Button("Submit");
		Button closeButton=new Button("Close");
		HorizontalPanel submitAndClose=new HorizontalPanel();
		
		submitAndClose.add(submit);
		submitAndClose.add(closeButton);
		vp.add(submitAndClose);

		d.setWidget(vp);
		d.center();

		closeButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				d.hide();
			}
			
		});
		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String tag = comments.getText();
				if (!tag.equals(""))
					db.commentPhoto(pid, uid, tag, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Label l = new Label(
									"Remote Procedure Call -- Failure");
							d.setWidget(l);
							d.center();
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							vp.clear();
							Label l = new Label("Done");
							Button close = new Button("Close");
							vp.add(l);
							vp.add(close);
							d.setWidget(vp);
							d.center();
							close.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									d.hide();
								}
							});
						}

					});
				String s = score.getItemText(score.getSelectedIndex());
				db.commentPhoto(pid, uid, Integer.parseInt(s),
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Label l = new Label(
										"Remote Procedure Call -- Failure");
								d.setWidget(l);
								d.center();
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub

							}

						});
			}

		});

	}
}
