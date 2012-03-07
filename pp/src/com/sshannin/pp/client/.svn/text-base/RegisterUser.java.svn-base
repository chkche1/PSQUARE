package com.sshannin.pp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sshannin.pp.client.stubs.DBRoutinesAsync;

public class RegisterUser implements ClickHandler {
	final DBRoutinesAsync db;
	String f_name;
	String l_name;
	String email;
	int year;
	String gender;
	String address;
	String pwd;

	final DialogBox d = new DialogBox(false,true);
	final TextBox firstInput = new TextBox();
	final TextBox lastInput = new TextBox();
	final TextBox emailInput = new TextBox();
	final ListBox yearChoose = new ListBox();
	final ListBox genderChoose = new ListBox();
	final TextBox addressInput = new TextBox();
	final TextBox pwdInput = new TextBox();
	final TextBox pwdAgainInput = new TextBox();
	final Label errorLabel = new Label();
	final Button submitBtn = new Button("Submit");
	final Button closeBtn = new Button("Close");

	public RegisterUser(DBRoutinesAsync db) {
		this.db = db;
	}
	
	public RegisterUser(){
		this.db=null;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		register();
	}

	private void register() {

		d.setText("Welcome register");
		d.setWidth("200px");
		VerticalPanel vp = new VerticalPanel();
		vp.setWidth("200px");
		vp.add(new Label("First name"));

		vp.add(firstInput);

		vp.add(new Label("Last name"));

		vp.add(lastInput);

		vp.add(new Label("e-mail address"));

		vp.add(emailInput);

		vp.add(new Label("Year of Birth"));

		for (int i = 1900; i <= 2011; i++)
			yearChoose.addItem(i + "", i + "");
		yearChoose.setVisibleItemCount(1);
		vp.add(yearChoose);

		vp.add(new Label("Gender"));

		genderChoose.addItem("M", "M");
		genderChoose.addItem("F", "F");
		genderChoose.setVisibleItemCount(1);
		vp.add(genderChoose);

		vp.add(new Label("Address"));

		vp.add(addressInput);

		vp.add(new Label("Password"));

		vp.add(pwdInput);
		vp.add(new Label("Password Again"));

		vp.add(pwdAgainInput);

		vp.add(errorLabel);

		HorizontalPanel hp = new HorizontalPanel();
		hp.setWidth("200px");
		submitBtn.setWidth("80px");
		closeBtn.setWidth("80px");
		hp.add(submitBtn);
		hp.add(closeBtn);
		vp.add(hp);
	
		d.setWidget(vp);
		d.center();
		submitBtn.addClickHandler(new submitHandler());
		closeBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				d.hide();
			}
			
		});
	}

	class submitHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(verifyInfo()==false)
				return;
			db.register(f_name, l_name, email, year, gender, address, pwd,
					new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							errorLabel.setText("Remote Procedure Call -- error");
							
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							d.hide();
							final DialogBox done=new DialogBox();
							Button close=new Button();
							done.setText("Congratulations! You are with P_SQUARE now");
							done.add(close);
							done.setHeight("20px");
							done.setWidth("100px");
							close.addClickHandler(new ClickHandler(){

								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									done.hide();
								}});
						}
					});
		}
		
		private boolean verifyInfo(){
			f_name=firstInput.getText();
			if(f_name.equals("")){
				errorLabel.setText("First name cannot be null");
				return false;
			}
			l_name=lastInput.getText();
			if(l_name.equals("")){
				errorLabel.setText("Last name cannot be null");
				return false;
			}
			email=emailInput.getText();
			if(email.equals("")||email.contains("@")==false){
				errorLabel.setText("Invalid email address");
				return false;
			}
			String yearChosen=yearChoose.getItemText(yearChoose.getSelectedIndex());
			year=Integer.parseInt(yearChosen);
			assert(year>=1900 && year<=2011);
			
			gender=genderChoose.getItemText(genderChoose.getSelectedIndex());
			assert(gender.equals("M")||gender.equals("F"));
			
			address=addressInput.getText();
			
			pwd=pwdInput.getText();
			if(pwd.equals("")){
				errorLabel.setText("Invalid password");
				return false;
			}
			
			String pwd1=pwdAgainInput.getText();
			if(!pwd1.equals(pwd)){
				errorLabel.setText("Password not matching");
				return false;
			}
			return true;
		}

	}
	
}
