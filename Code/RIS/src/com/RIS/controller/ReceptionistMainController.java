package com.RIS.controller;

import com.RIS.model.Appointment;
import com.RIS.model.Bill;
import com.RIS.model.Order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReceptionistMainController {

		
		@FXML private TableView<Order> tablieViewOrders;
		@FXML private TableView<Appointment> tablieViewAppointment;
		@FXML private TableView<Bill> tableViewBill;
		
	    @FXML
	    private TableColumn<?, ?> colOrderId;

	    @FXML
	    private TableColumn<?, ?> colEmergency;

	    @FXML
	    private TableColumn<?, ?> colUserId;

	    @FXML
	    private TableColumn<?, ?> colPatientId;

	    @FXML
	    private TableColumn<?, ?> colModalityId;

	    @FXML
	    private TableColumn<?, ?> colStartTime;

	    @FXML
	    private TableColumn<?, ?> colModality;

	    @FXML
	    private TableColumn<?, ?> colFirstName;

	    @FXML
	    private TableColumn<?, ?> colLastName;

	    @FXML
	    private TableColumn<?, ?> ColPatientidApp;

	    @FXML
	    private TableColumn<?, ?> colNotesApp;

	    @FXML
	    private TableColumn<?, ?> billId;

	    @FXML
	    private TableColumn<?, ?> billCost;

	    @FXML
	    private TableColumn<?, ?> appIdBill;

	    @FXML
	    private TableColumn<?, ?> userIdBill;

	    @FXML
	    private TableColumn<?, ?> patientIdBill;

	    @FXML
	    private TableColumn<?, ?> modalityIdBill;
	    
	    @FXML
	    void initialize() {
	    	
	        assert colOrderId != null : "fx:id=\"colOrderId\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colEmergency != null : "fx:id=\"colEmergency\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colUserId != null : "fx:id=\"colUserId\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colPatientId != null : "fx:id=\"colPatientId\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colModalityId != null : "fx:id=\"colModalityId\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colStartTime != null : "fx:id=\"colStartTime\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colModality != null : "fx:id=\"colModality\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colFirstName != null : "fx:id=\"colFirstName\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colLastName != null : "fx:id=\"colLastName\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert ColPatientidApp != null : "fx:id=\"ColPatientidApp\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert colNotesApp != null : "fx:id=\"colNotesApp\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert billId != null : "fx:id=\"billId\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert billCost != null : "fx:id=\"billCost\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert appIdBill != null : "fx:id=\"appIdBill\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert userIdBill != null : "fx:id=\"userIdBill\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert patientIdBill != null : "fx:id=\"patientIdBill\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";
	        assert modalityIdBill != null : "fx:id=\"modalityIdBill\" was not injected: check your FXML file 'ReceptionistMain.fxml'.";

	    }

	    @FXML
	    void NewAppfromOrder(ActionEvent event) {

	    }

	    @FXML
	    void markasDenied(ActionEvent event) {

	    }

	    @FXML
	    void markasReceived(ActionEvent event) {

	    }

	    @FXML
	    void onSelectBilling(ActionEvent event) {

	    }

	    @FXML
	    void onSelectOrder(ActionEvent event) {

	    }

	    @FXML
	    void onSelectSchedule(ActionEvent event) {

	    }

	

}
