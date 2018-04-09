package com.RIS.controller;

import com.RIS.model.Appointment;
import com.RIS.model.Bill;
import com.RIS.model.Order;
import com.RIS.model.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReceptionistMainController {

		@FXML private TableView<Order> tablieViewOrders;
		@FXML private TableView<Appointment> tablieViewAppointment;
		@FXML private TableView<Bill> tableViewBill;
	    @FXML private TableColumn<Order, Integer> colOrderId, colModalityId;
	    @FXML private TableColumn<Order, String> colEmergency, colUserId, colPatientId;
	    @FXML private TableColumn<Appointment, String> colStartTime, colStopTime, colModality, colFirstName, colLastName, ColPatientidApp, colNotesApp;
	    @FXML private TableColumn<Bill, Integer> billId, appIdBill, modalityIdBill;
	    @FXML private TableColumn<Bill, Double> billCost;
	    @FXML private TableColumn<Bill, String> userIdBill, patientIdBill;
	    
	    @FXML
	    void initialize() {  	
	        //set up the columns in the tables
	    	//Order Table
	    	colOrderId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
	    	colEmergency.setCellValueFactory(new PropertyValueFactory<Order, String>("emergencylevel"));
	    	colUserId.setCellValueFactory(new PropertyValueFactory<Order, String>("userID"));
	    	colPatientId.setCellValueFactory(new PropertyValueFactory<Order, String>("patientID"));
	    	colModalityId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("modalityID"));
			
	    	//Appointment Table
	    	colStartTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("startTime"));
	    	colStopTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("stopTime"));
	    	colModality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modalityID"));
	    	colFirstName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("firstName"));
	    	colLastName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastName"));
	    	ColPatientidApp.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientID"));
	    	colNotesApp.setCellValueFactory(new PropertyValueFactory<Appointment, String>("notes"));
	    	
	    	//Billing Table
	    	billId.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billID"));
	    	billCost.setCellValueFactory(new PropertyValueFactory<Bill, Double>("Cost"));
	    	appIdBill.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("appID"));
	    	userIdBill.setCellValueFactory(new PropertyValueFactory<Bill, String>("userID"));
	    	patientIdBill.setCellValueFactory(new PropertyValueFactory<Bill, String>("patientID"));
	    	modalityIdBill.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("ModalityID"));

			//tableViewInfo.setItems(getPatientList());
			tablieViewOrders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			tablieViewAppointment.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			tableViewBill.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    	

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
