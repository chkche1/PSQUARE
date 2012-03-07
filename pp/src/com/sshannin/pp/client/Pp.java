package com.sshannin.pp.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sshannin.pp.client.stubs.DBRoutines;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;
import com.sshannin.pp.client.stubs.ServiceDispatcher;
import com.sshannin.pp.client.stubs.ServiceDispatcherAsync;
import com.sshannin.pp.client.stubs.XQRoutines;
import com.sshannin.pp.client.stubs.XQRoutinesAsync;
import com.sshannin.pp.client.PhotoPosting;
import com.sshannin.pp.client.PhotoRateWindow;
import com.sshannin.pp.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pp implements EntryPoint {
	// Global User info
	Integer uid;
	String f_name;
	String l_name;
	String dob;
	String email;
	String gender;
	String addr;
	LinkedList<String> institutions;
	HashMap<String, String> attributes;
	LinkedList<String> advisees;
	PhotoPosting posting;
	PhotoRateWindow rating;
	LinkedList<Pic> pictures;

	// Info Grid
	private Grid info;
	private final static int infoCount = 10;

	private Grid recommend;
	private final static int recommendCount = 5;

	/**
	 * This is the graph visualization as an object
	 */
	private JavaScriptObject theGraph;

	// User state
	boolean loggedIn;

	int MAX_IMAGE_HEIGHT = 400;
	int MAX_IMAGE_WIDTH = 300;

	// Photo Grid
	private Grid photos;
	private int photoIndex = 0;
	private static final int PHOTO_COUNT = 5;
	//private int photoPos = 0;
	//private LinkedList<Integer> pids;

	private String xml;

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */	

	private final DBRoutinesAsync dbRoutines = GWT.create(DBRoutines.class);

	private final XQRoutinesAsync xqRoutines = GWT.create(XQRoutines.class);

	// private final DisplayPhotoSearch dps=new DisplayPhotoSearch(dbRoutines);

	private final ServiceDispatcherAsync dispatcher = GWT
	.create(ServiceDispatcher.class);

	private static final String LOGIN_TEXT="Please enter your login info";

	private final class TextClearer implements ClickHandler{
		private com.google.gwt.user.client.ui.TextBoxBase wi;
		public TextClearer(com.google.gwt.user.client.ui.TextBoxBase _wi){
			wi = _wi;
		}
		@Override
		public void onClick(ClickEvent event) {
			wi.setText("");
		}
	}

	final DialogBox dialogBox = new DialogBox();
	final HTML serverResponseLabel = new HTML();
	final Button closeButton = new Button("Close");
	/**
	 * This is the entry point method.
	 */

	private UpdateDisplay updis;
	public void onModuleLoad() {
		//updis=new UpdateDisplay(5003);
		xqRoutines.readXML(new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				System.out.print(caught.getMessage());
				caught.printStackTrace();
			}

			public void onSuccess(String result) {
				xml = result;
			}
		});

		ensureBGServices();
		ChatHandler.beginChatServices();
		loggedIn = false;
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("user id or email");
		nameField.addClickHandler(new TextClearer(nameField));

		final TextBox passwordField = new PasswordTextBox();
		passwordField.setText("password");
		passwordField.addClickHandler(new TextClearer(passwordField));
		//textBox.getElement().setPropertyString("type", "password");


		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		/*		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		 */
		// Focus the cursor on the name field when the app loads

		final DialogBox loginBox=new DialogBox(false,true);
		loginBox.setHeight("20px");
		loginBox.setText(LOGIN_TEXT);
		//Label l=new Label(LOGIN_TEXT);
		HorizontalPanel inputHp=new HorizontalPanel();
		inputHp.add(nameField);
		inputHp.add(passwordField);
		inputHp.add(sendButton);

		final Button registerButton=new Button("New User? Click me");
		registerButton.setWidth("160px");
		registerButton.addClickHandler(new RegisterUser(dbRoutines));
		inputHp.add(registerButton);

		VerticalPanel vp=new VerticalPanel();
		vp.addStyleName("dialogVPanel");
		//vp.add(l);
		//vp.add(new HTML("<b>"+LOGIN_TEXT+"</b>"));
		vp.add(inputHp);
		vp.add(errorLabel);
		vp.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		loginBox.setWidget(vp);
		loginBox.center();

		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		//final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		//final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label loginToServerLabel = new Label();
		//final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Welcome:</b>"));
		dialogVPanel.add(loginToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
				if (loggedIn)
					loadLoggedIn();
			}
		});

		// Create a handler for the sendButton and nameField
		class LoginEventHandler implements ClickHandler, KeyUpHandler {

			// Fired when the user clicks on the sendButton.

			public void onClick(ClickEvent event) {
				authenticateUser();
			}

			// Fired when the user types in the nameField.

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					authenticateUser();
				}
			}

			private void authenticateUser() {
				// First, we validate the input.
				errorLabel.setText("");
				final String loginToServer = nameField.getText();
				if (!FieldVerifier.isValidName(loginToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				final String passwordToServer = passwordField.getText();
				/*
				 * if (!FieldVerifier.isValidName(passwordToServer)) {
				 * errorLabel.setText("Please enter at least four characters");
				 * return; }
				 */

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				loginToServerLabel.setText(loginToServer);

				serverResponseLabel.setText("");
				dbRoutines.authenticate(loginToServer, passwordToServer,
						new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox
						.setText("Remote Procedure Call - Failure");
						serverResponseLabel
						.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						System.out.print(caught.getMessage());
						caught.printStackTrace();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						if (result.isEmpty()) {
							dialogBox
							.setText("Oops! You do not exist!");
						} else {
							loginBox.hide();
							loggedIn = true;
							// Parse result
							String[] tokens = result.split("#");
							uid = Integer.valueOf(tokens[0]);
							f_name = tokens[1];
							l_name = tokens[2];
							dob = tokens[3];
							email = tokens[4];
							gender = tokens[5];
							addr = tokens[6];

							// Initialize complex data struct
							institutions = new LinkedList<String>();
							attributes = new HashMap<String, String>();
							advisees = new LinkedList<String>();
							posting = new PhotoPosting(dbRoutines, uid);
							//rating = new PhotoRateWindow(dbRoutines, uid);
							//pids = new LinkedList<Integer>();
							pictures = new LinkedList<Pic>();
							photoIndex = 0;

							dialogBox.setText("Welcome to PSQUARE!");
							serverResponseLabel.setHTML(f_name
									+ "! How are you!?");
						}
						serverResponseLabel
						.removeStyleName("serverResponseLabelError");

						dialogBox.center();
						closeButton.setFocus(true);
					}
				});

			}// end of authenticateUser
		}

		// Add a handler to send the name to the server
		LoginEventHandler handler = new LoginEventHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		passwordField.addKeyUpHandler(handler);

	}// end of onModuleLoad

	HorizontalPanel hp;

	private void loadLoggedIn() {
		/*		RootPanel.get("nameFieldContainer").clear();
		RootPanel.get("passwordFieldContainer").clear();
		RootPanel.get("sendButtonContainer").clear();
		RootPanel.get("errorLabelContainer").clear();
		 */
		// Pop-out Setup
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label loginToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Welcome:</b>"));
		dialogVPanel.add(loginToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		updis=new UpdateDisplay(uid);

		// Create Photo post button
		class postPhotoHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				dbRoutines.getPID(new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel
						.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);

					}

					public void onSuccess(Integer result) {
						posting.post(result);
					}
				});
			}// end of onClick
		} // end of postPhotoHandler

		Button postButton = new Button("Post a Photo");
		RootPanel.get("photoPostContainer").add(postButton);
		postPhotoHandler handler = new postPhotoHandler();
		postButton.addClickHandler(handler);
		postButton.addStyleName("BlueBorder");

		// Create Photo Search
		final Button searchButton = new Button("Search");
		final TextBox searchField = new TextBox();
		searchField.setText("Enter search tags");
		RootPanel.get("tagFieldContainer").add(searchField);
		RootPanel.get("photoSearchContainer").add(searchButton);
		// final VerticalPanel vp = new VerticalPanel();
		// final DockPanel dp = new DockPanel();
		final DialogBox sdb = new DialogBox();
		sdb.setText("Search Results");
		// sdb.setWidget(dp);
		// dp.setSpacing(5);



		class searchFieldHandler implements KeyUpHandler {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					//new Searcher().search();
					DisplayPhotoSearch dps=new DisplayPhotoSearch(dbRoutines,uid);
					String kw=searchField.getText();
					dps.search(kw);
				}
			}	
		}

		class searchButtonHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				//new Searcher().search();
				DisplayPhotoSearch dps=new DisplayPhotoSearch(dbRoutines,uid);
				String kw=searchField.getText();
				dps.search(kw);
			}
		}

		// end of searchHandler
		searchButton.addClickHandler(new searchButtonHandler());
		searchField.addKeyUpHandler(new searchFieldHandler());
		searchField.addClickHandler(new TextClearer(searchField));

		// Display Personal Profile
		info = new Grid(infoCount + 1, 1);
		HorizontalPanel hp1 = new HorizontalPanel();
		info.setWidget(infoCount, 0, hp1);
		RootPanel.get("infoGrid").add(info);

		info.setHTML(0, 0, "<h4>Name: " + f_name + " " + l_name + "</h4>");
		info.setHTML(1, 0, "<h4>DOB: " + dob + "</h4>");
		info.setHTML(2, 0, "<h4>Email: " + email + "</h4>");
		info.setHTML(3, 0, "<h4>Gender: " + gender + "</h4>");
		info.setHTML(4, 0, "<h4>Address: " + addr + "</h4>");

		recommend = new Grid(recommendCount + 1, 1);
		HorizontalPanel hp2 = new HorizontalPanel();
		recommend.setWidget(recommendCount, 0, hp2);
		RootPanel.get("recommendationContainer").add(recommend);

		recommend.setHTML(0, 0, "<h3>People you might know:</h3>"); 

		dbRoutines.friendRecommendation(uid, new AsyncCallback<List<Integer>>() {
			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Integer> result) {
				if (result.isEmpty()) {
					recommend.setHTML(1, 0, "<h3>No friend recommendations at this point</h3>");
				} else {
					final int[] row = new int[1];
					row[0] = 1;
					for (final Integer i : result) {
						if (row[0] < 5){
							dbRoutines.getNameFromID(i, new AsyncCallback<String>() {
								public void onFailure(Throwable caught) {
									dialogBox.setText("Remote Procedure Call - Failure");
									serverResponseLabel.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
								}

								public void onSuccess(String result){
									if (row[0] < 5){
										//recommend.setHTML(row[0], 0, "<h3>" + result +  "</h3>");
										HTML html=new HTML("<h3>" + result +  "</h3>");
										html.addClickHandler(new Friending(dbRoutines,uid,i));
										recommend.setWidget(row[0], 0, html);
										row[0]++;
									}
								}
							});

						}
					}
				}

			} // end of onSuccess
		}); // end of friendRecommendations
		/*
		list = new Grid(listCount + 1, 1);
		HorizontalPanel hp3 = new HorizontalPanel();
		list.setWidget(listCount, 0, hp3);
		RootPanel.get("friendListContainer").add(list);

		list.setHTML(0, 0, "<h3>Your Friends:</h3>"); */

		dbRoutines.getFriends(uid, new AsyncCallback<List<Integer>>() {
			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Integer> result) {
				final VerticalPanel flowDiv = new VerticalPanel();
				final ScrollPanel scroller = new ScrollPanel(flowDiv);
				scroller.setHeight("100%");

				final TextArea input = new TextArea();
				input.setWidth("100%");

				RootPanel.get("friendFlow").add(scroller);

				flowDiv.add(new HTML("<br/>")); //pad top
				flowDiv.add(new HTML("<h3> Your friends: </h3>" ));
				final int[] listCount = new int[1];
				listCount[0] = result.size();

				if (result.isEmpty()) {
					flowDiv.add(new HTML("<h4>You have no friends at this point</h4>"));
				} else {
					final int[] row = new int[1];
					row[0] = 1;
					for (Integer i : result) {
						dbRoutines.getNameFromID(i, new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								dialogBox.setText("Remote Procedure Call - Failure");
								serverResponseLabel.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result){	
								flowDiv.add(new HTML("<p>" + result + "<p>"));
								scroller.scrollToBottom();
								//list.setHTML(row[0], 0, "<h3>" + result +  "</h3>");
								row[0]++;
							}
						});
					}
				}

			} // end of onSuccess
		}); // end of getFriends

		xqRoutines.resetLoggedIn(new AsyncCallback<String>(){
			public void onFailure(Throwable caught){
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}
			public void onSuccess(String result){
				//Do nothing. Just resets user by itself
			}
		});
		visualizeUser();

		dbRoutines.getInstitutions(uid, new AsyncCallback<List<String>>() {
			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<String> result) {
				if (result.isEmpty()) {
					info.setHTML(5, 0, "<h3>Academic Institutions: None</h3>");
				} else {
					StringBuffer b = new StringBuffer();

					for (String i : result) {
						institutions.add(i);
						// System.out.println("added to institutions: "+i);
						b.append(i + ",");
					}
					b.deleteCharAt(b.length() - 1);
					info.setHTML(5, 0,
							"<h3>Academic Institutions:" + b.toString()
							+ "</h3>");
				}

			} // end of onSuccess
		}); // end of getInstitutions

		dbRoutines.getTypeAttributes(uid,
				new AsyncCallback<HashMap<String, String>>() {

			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel
				.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(HashMap<String, String> result) {
				int attr_cnt = 6;

				for (Map.Entry<String, String> entry : result
						.entrySet()) {
					String key = entry.getKey();
					String val = entry.getValue();

					try {
						Integer.parseInt(key);
						advisees.add(val);
					} catch (NumberFormatException e) {

						attributes.put(key, val);
						info.setHTML(attr_cnt, 0, "<h3>" + key + ": "
								+ val + "</h3>");
						attr_cnt++;
					}
				}// end of foreach loop

				// System.out.println("map size: "+attributes.size());

				if (!advisees.isEmpty()) {
					StringBuffer sbuf = new StringBuffer();
					for (String s : advisees) {
						sbuf.append(s + ",");
					}
					// get rid of punctuation
					sbuf.deleteCharAt(sbuf.length() - 1);
					info.setHTML(attr_cnt, 0,
							"<h3>Advisees: " + sbuf.toString()
							+ "</h3>");
				}

			}// end of onSuccess
		}); // end of getTypeAttributes

		// Setup photo grid for displaying relevant photo
		photos = new Grid(PHOTO_COUNT + 1, 1);
		hp = new HorizontalPanel();
		final Button galleryNextButton = new Button("Next");
		//		RootPanel.get("galleryNextButton").add(galleryNextButton);
		final Button galleryPrevButton = new Button("Previous");
		HorizontalPanel buttons=new HorizontalPanel();
		buttons.add(galleryPrevButton);
		buttons.add(galleryNextButton);
		RootPanel.get("galleryButtons").add(buttons);
		//		RootPanel.get("galleryPrevButton").add(galleryPrevButton);

		// add handlers to buttons
		galleryNextButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				hp.clear(); // clear old photos
				for(int k =0; k<PHOTO_COUNT; k++){
					photoIndex++;
					if(photoIndex>=pictures.size()){
						photoIndex = 0;
					}
					Pic p = pictures.get(photoIndex);
					Image img = new Image(p.url);
					img.setPixelSize(200, 200);
					hp.add(img);
				}// end of for

			}
		});

		galleryPrevButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				hp.clear();
				for(int k=0; k<PHOTO_COUNT; k++){
					photoIndex--;
					if(photoIndex<0)
						photoIndex = pictures.size()-1;
					Pic p = pictures.get(photoIndex);
					Image img = new Image(p.url);
					img.setPixelSize(200, 200);
					hp.add(img);
				}
			}
		});


		photos.setWidget(PHOTO_COUNT, 0, hp);
		RootPanel.get("photoDisplayContainer").add(photos);

		dbRoutines.getPhotosByRelevanceCached(uid,
				new AsyncCallback<List<Integer>>() {
			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel
				.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Integer> result) {
				loadPhotoToPanel(result);
			}

		});

	} // end of loadLoggedIn

	int cnt = 0;
	int pid = 0;
	int tmp = 0;
	String url;

	public void loadPhotoToPanel(List<Integer> li) {
		cnt = 0;
		for (; cnt < PHOTO_COUNT; cnt++) {
			pid = li.get(cnt);
			pictures.clear();
			// System.out.println("loop var: " + cnt);
			dbRoutines.getURL(pid, new AsyncCallback<String>() {
				int cbpid = pid;

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("can't load photo");
					caught.printStackTrace();
				}

				@Override
				public void onSuccess(final String result) {
					final Image img = new Image(result);
					img.addLoadHandler(new LoadHandler(){
						public void onLoad(LoadEvent event) {
							Element element = (Element) event
							.getRelativeElement();
							if (element == img.getElement()) {
								int originalHeight = img
								.getOffsetHeight();
								int originalWidth = img
								.getOffsetWidth();
								if (originalHeight > originalWidth) {
									img.setHeight(MAX_IMAGE_HEIGHT
											+ "px");
								} else {
									img.setWidth(MAX_IMAGE_WIDTH
											+ "px");
								}
							}

						}

					});


					img.addClickHandler(new PhotoRateWindow(dbRoutines, uid, cbpid, result));
					hp.add(img);
					Pic p = new Pic(pid, result);
					pictures.add(p);
				}
			}); // end of getURL
		} // end of for loop

		// Read the rest of the photos into a list for next, prev buttons later
		for(; cnt<li.size(); cnt++){
			pid = li.get(cnt);
			// System.out.println("loop var: " + cnt);
			dbRoutines.getURL(pid, new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("can't load photo");
					caught.printStackTrace();
				}

				@Override
				public void onSuccess(final String result) {
					Pic p = new Pic(pid, result);
					pictures.add(p);
				}
			}); // end of getURL
		}// end of for
	}

	/**
	 * Visualize the user and his/her neighbors
	 * 
	 */

	public void visualizeUser() {
		// Request the person and his/her neighbors
		//System.out.println("Lets see: "+ uid);
		xqRoutines.getPersonAndNeighbors(xml, uid, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Unable to execute XQuery");

			}

			@Override
			public void onSuccess(String result) {
				try {
					JSONObject x = XMLandJSON.getJsonForUser(XMLandJSON
							.parseXML(result));
					if (x != null) {
						//System.out.println("this is x=" + x.toString());
					//	System.out.println("Hello There: " + uid);
						theGraph = FriendVisualization.createGraph(
								x.toString(), Pp.this);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Request the person's photos
		// loadPhotos();
		// displayUserContent();

	}

	/**
	 * Incrementally redraw graph for the selected person. Also update the left
	 * and right-hand panes
	 * 
	 * @param result
	 */
	public void drawNodeAndNeighbors(int person) {
		// currentPerson = person;
		// firstPhoto = 0;
		//System.out.println("Requesting data for " + person);
		//System.out.println("Hello there!!!");
		xqRoutines.getPersonAndNeighbors(xml, person,
				new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(SERVER_ERROR);

			}

			@Override
			public void onSuccess(String result) {
				com.google.gwt.xml.client.Node n = XMLandJSON
				.parseXML(result);

				if (n != null) {
					FriendVisualization.addToGraph(theGraph, XMLandJSON
							.getJsonForUser(n).toString());
				}
			}

		});


		//RootPanel.get("galleryNextButton").clear();
		//RootPanel.get("galleryPrevButton").clear();
		RootPanel.get("photoDisplayContainer").clear();

		photos = new Grid(PHOTO_COUNT + 1, 1);
		hp = new HorizontalPanel();
		//final Button galleryNextButton = new Button("Next");
		//RootPanel.get("galleryNextButton").add(galleryNextButton);
		//final Button galleryPrevButton = new Button("Previous");
		//RootPanel.get("galleryPrevButton").add(galleryPrevButton);

		photos.setWidget(PHOTO_COUNT, 0, hp);
		RootPanel.get("photoDisplayContainer").add(photos);

		dbRoutines.getPhotosByRelevanceCached(person,
				new AsyncCallback<List<Integer>>() {
			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel
				.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Integer> result) {
				System.out.println("list size: " + result.size());
				loadPhotoToPanel(result);
			}

		});


		//RootPanel.get("galleryNextButton").clear();
		//RootPanel.get("galleryPrevButton").clear();
		RootPanel.get("photoDisplayContainer").clear();

		photos = new Grid(PHOTO_COUNT + 1, 1);
		hp = new HorizontalPanel();
		//final Button galleryNextButton = new Button("Next");
		//RootPanel.get("galleryNextButton").add(galleryNextButton);
		//final Button galleryPrevButton = new Button("Previous");
		//RootPanel.get("galleryPrevButton").add(galleryPrevButton);

		photos.setWidget(PHOTO_COUNT, 0, hp);
		RootPanel.get("photoDisplayContainer").add(photos);

		dbRoutines.getPhotosByRelevanceCached(person,
				new AsyncCallback<List<Integer>>() {
			@Override
			public void onFailure(Throwable caught) {
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel
				.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Integer> result) {
				//System.out.println("list size: " + result.size());
				loadPhotoToPanel(result);
			}

		});

		final int[] persons = new int[1];
		persons[0] = person;
		RootPanel.get("infoGrid").clear();
		int i = 0;
		while (i < infoCount){
			info.setHTML(i, 0, "");
			i++;
		}
		dbRoutines.getInfo(person, 
				new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// System.out.print("login: "+loginToServer+" pwd: "+passwordToServer);
				// Show the RPC error message to the user
				dialogBox
				.setText("Remote Procedure Call - Failure");
				serverResponseLabel
				.addStyleName("serverResponseLabelError");
				// System.out.println("I'm here");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				System.out.print(caught.getMessage());
				closeButton.setFocus(true);
			}

			public void onSuccess(String result) {
				//if (result.isEmpty()) {

				//} else {
				//loginBox.hide();
				//	loggedIn = true;
				// Parse result
				String[] tokens = result.split("#");
				// System.out.println(result);
				uid = Integer.valueOf(tokens[0]);
				f_name = tokens[1];
				l_name = tokens[2];
				dob = tokens[3];
				email = tokens[4];
				gender = tokens[5];
				addr = tokens[6];

				//	RootPanel.get("infoGrid").clear();

				info.setHTML(0, 0, "<h4>Name: " + f_name + " " + l_name + "</h4>");
				info.setHTML(1, 0, "<h4>DOB: " + dob + "</h4>");
				info.setHTML(2, 0, "<h4>Email: " + email + "</h4>");
				info.setHTML(3, 0, "<h4>Gender: " + gender + "</h4>");
				info.setHTML(4, 0, "<h4>Address: " + addr + "</h4>");

				// Initialize complex data struct
				institutions = new LinkedList<String>();
				attributes = new HashMap<String, String>();
				advisees = new LinkedList<String>();
				posting = new PhotoPosting(dbRoutines, persons[0]);
				//pids = new LinkedList<Integer>();
				pictures = new LinkedList<Pic>();
				photoIndex = 0;

				dbRoutines.getInstitutions(persons[0], new AsyncCallback<List<String>>() {
					@Override
					public void onFailure(Throwable caught) {
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					@Override
					public void onSuccess(List<String> result) {
						if (result.isEmpty()) {
							info.setHTML(5, 0, "<h3>Academic Institutions: None</h3>");
						} else {
							StringBuffer b = new StringBuffer();

							for (String i : result) {
								institutions.add(i);
								// System.out.println("added to institutions: "+i);
								b.append(i + ",");
							}
							b.deleteCharAt(b.length() - 1);
							info.setHTML(5, 0,
									"<h3>Academic Institutions:" + b.toString()
									+ "</h3>");
						}

					} // end of onSuccess
				}); // end of getInstitutions

				dbRoutines.getTypeAttributes(uid,
						new AsyncCallback<HashMap<String, String>>() {

					@Override
					public void onFailure(Throwable caught) {
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel
						.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					@Override
					public void onSuccess(HashMap<String, String> result) {
						int attr_cnt = 6;

						for (Map.Entry<String, String> entry : result
								.entrySet()) {
							String key = entry.getKey();
							String val = entry.getValue();

							try {
								Integer.parseInt(key);
								advisees.add(val);
							} catch (NumberFormatException e) {

								attributes.put(key, val);
								info.setHTML(attr_cnt, 0, "<h3>" + key + ": "
										+ val + "</h3>");
								attr_cnt++;
							}
						}// end of foreach loop

						// System.out.println("map size: "+attributes.size());

						if (!advisees.isEmpty()) {
							StringBuffer sbuf = new StringBuffer();
							for (String s : advisees) {
								sbuf.append(s + ",");
							}
							// get rid of punctuation
							sbuf.deleteCharAt(sbuf.length() - 1);
							info.setHTML(attr_cnt, 0,
									"<h3>Advisees: " + sbuf.toString()
									+ "</h3>");
						}

					}// end of onSuccess
				}); // end of getTypeAttributes
				RootPanel.get("infoGrid").add(info);
				//}

			}
		});

		// loadPhotos();
		// displayUserContent();
	}

	public void ensureBGServices() {
		dispatcher.startServices(new AsyncCallback<Integer>() {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Couldn't verify background services.");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Integer result) {
				//System.out.println("Background services verified as present.");
			}

		});
	}

	private class Pic {
		int pid;
		String url;

		Pic(int id, String l) {
			this.pid = id;
			this.url = l;
		}
		public String toString(){
			return "pid: "+this.pid+" url: "+this.url+"\n";
		}
	}
}


