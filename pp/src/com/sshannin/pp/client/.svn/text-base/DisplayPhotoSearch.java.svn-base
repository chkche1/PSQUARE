package com.sshannin.pp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;
import com.sshannin.pp.client.stubs.PhotoSearch;
import com.sshannin.pp.client.stubs.PhotoSearchAsync;

import java.util.*;
import java.util.Map.Entry;

public class DisplayPhotoSearch {
	final String DEFAULT = "Type a keyword";
	final int IMAGE_PER_PAGE = 4;

	final DBRoutinesAsync db;
	final int uid;

	PhotoSearchAsync photoAsync = GWT.create(PhotoSearch.class);

	final DialogBox dialog = new DialogBox();

	TextBox kwBox = new TextBox();
	Button searchButton = new Button("Search");
	Button closeButton = new Button("Close");

	public DisplayPhotoSearch(DBRoutinesAsync db,int userid) {
		this.db = db;
		this.uid=userid;
		closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dialog.hide();
			}

		});
	}

	public Integer display() throws IllegalArgumentException {

		dialog.setText("Seach Photo by Keyword");
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(kwBox);
		hp.add(searchButton);
		dialog.setWidget(hp);
		dialog.center();
		KWBoxHandler mHandler = new KWBoxHandler();
		kwBox.addClickHandler(mHandler);
		kwBox.addKeyPressHandler(mHandler);

		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String keyword = kwBox.getText();
				if (!keyword.equals(DEFAULT))
					search(keyword);
			}

		});
		return 0;
	}

	public void search(String keyword) throws IllegalArgumentException {
		dialog.setText("Search Results");
		dialog.clear();
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel top = new HorizontalPanel();

		final Button previous = new Button("Previous");
		final Button next = new Button("Next");

		final Label totalResults = new Label();
		top.add(previous);
		top.add(next);
		top.add(totalResults);
		top.add(closeButton);
		vp.add(top);
		HorizontalPanel pics = new HorizontalPanel();
		final Image imgList[] = new Image[IMAGE_PER_PAGE];

		for (int i = 0; i < IMAGE_PER_PAGE; i++) {
			Image img = new Image();
			
			img.setPixelSize(200, 200);
			imgList[i] = img;
			pics.add(imgList[i]);
		}
		vp.add(pics);
		dialog.setWidget(vp);
		dialog.center();
		final List<String> urlList = new ArrayList<String>();
		final ArrayList<Integer> pidList=new ArrayList<Integer>();
		final int start[] = new int[] { 0 };
		final int total[] = new int[] { 0 };

		photoAsync.lookupTag(keyword,
				new AsyncCallback<Map<Integer, String>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Map<Integer, String> result) {
						// TODO Auto-generated method stub
						Iterator<Entry<Integer, String>> it = result.entrySet()
								.iterator();

			//			System.out.println("Got pid set result");
						while (it.hasNext()) {
							Map.Entry<Integer, String> pair = (Map.Entry<Integer, String>) it
									.next();
			//				System.out.println("I got photo:" + pair.getValue());
							urlList.add(pair.getValue());
							pidList.add(pair.getKey());
							// pidSet.add(pid)
						}

						total[0] = urlList.size();
						//System.out.println("total:" + total[0]);
						// totalResults.setText("Total result: "+total[0]);
						int toDisplay = total[0] - start[0];
						if (toDisplay <= IMAGE_PER_PAGE) {
							next.setEnabled(false);
							for (int i = toDisplay; i < IMAGE_PER_PAGE; i++)
								imgList[i].setVisible(false);
						} else {
							next.setEnabled(true);
							toDisplay = IMAGE_PER_PAGE;
						}
						previous.setEnabled(false);

						for (int i = 0; i < toDisplay; i++) {
							String url=urlList.get(start[0] + i);
							int pid=pidList.get(start[0] + i);
							imgList[i].setUrl(url);
							imgList[i].addClickHandler(new PhotoRateWindow(db,uid,pid,url));
						}
					}

				});

		next.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				start[0] = start[0] + IMAGE_PER_PAGE;

				if (!previous.isEnabled())
					previous.setEnabled(true);

				for (int i = 0; i < IMAGE_PER_PAGE; i++) {
					if (!imgList[i].isVisible())
						imgList[i].setVisible(true);
				}
				assert (start[0] < total[0]);

				int toDisplay = total[0] - start[0];
				if (toDisplay <= IMAGE_PER_PAGE) {
					next.setEnabled(false);
				} else {
					next.setEnabled(true);
					toDisplay = IMAGE_PER_PAGE;
				}
				for (int i = 0; i < toDisplay; i++) {
					String url=urlList.get(start[0] + i);
					int pid=pidList.get(start[0] + i);
					imgList[i].setUrl(url);
					imgList[i].addClickHandler(new PhotoRateWindow(db,uid,pid,url));
				}
				dialog.center();
			}

		});

		previous.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				start[0] = start[0] - IMAGE_PER_PAGE;
				assert (start[0] >= 0);
				if (!next.isEnabled())
					next.setEnabled(true);
				if (start[0] < IMAGE_PER_PAGE)
					previous.setEnabled(false);
				for (int i = 0; i < IMAGE_PER_PAGE; i++) {
					String url=urlList.get(start[0] + i);
					int pid=pidList.get(start[0] + i);
					imgList[i].setUrl(url);
					imgList[i].addClickHandler(new PhotoRateWindow(db,uid,pid,url));
				}
				dialog.center();
			}

		});

		/*
		 * while(start<total){ if(start==0) previous.setEnabled(false); else
		 * previous.setEnabled(true); int toDisplay=total-start;
		 * 
		 * }
		 */

	}

	class KWBoxHandler implements ClickHandler, KeyPressHandler {

		@Override
		public void onKeyPress(KeyPressEvent event) {
			// TODO Auto-generated method stub
			clear();
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {
				String keyword = kwBox.getText();
				if (!keyword.equals(""))
					search(keyword);
			}
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			clear();
		}

		private void clear() {
			if (kwBox.getText().equals(DEFAULT))
				kwBox.setText("");
		}
	}
}
