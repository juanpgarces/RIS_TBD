package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RIS.model.Appointment;
import com.RIS.model.Bill;
import com.RIS.model.Order;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReceptionistMainController {

		@FXML private TableView<Order> tableViewOrder;
		@FXML private TableView<Appointment> tableViewAppointment;
		@FXML private TableView<Bill> tableViewBill;
		
	    @FXML private TableColumn<Order, Integer> colOrderId, colModalityId;
	    @FXML private TableColumn<Order, String> colEmergency, colUserId, colPatientId;
	    @FXML private TableColumn<Appointment, String> colStartTime, colStopTime, colFirstName, colLastName, ColPatientidApp, colNotesApp;
	    @FXML private TableColumn<Appointment, Integer> colModality;
	    @FXML private TableColumn<Bill, Integer> billId, appIdBill, modalityIdBill;
	    @FXML private TableColumn<Bill, Double> billCost;
	    @FXML private TableColumn<Bill, String> userIdBill, patientIdBill;
	    
	    @FXML
	    void initialize() {  	
	        //set up the columns in the tables
	    	//Order Table
	    	//colOrderId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
	    	colEmergency.setCellValueFactory(new PropertyValueFactory<Order, String>("emergencylevel"));
	    	colUserId.setCellValueFactory(new PropertyValueFactory<Order, String>("userID"));
	    	colPatientId.setCellValueFactory(new PropertyValueFactory<Order, String>("patientID"));
	    	colModalityId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("modalityID"));
			
	    	//Appointment Table
	    	colStartTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("startTime"));
	    	colStopTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("stopTime"));
	    	colModality.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("modalityID"));
	    	colFirstName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("firstName"));
	    	colLastName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastName"));
	    	ColPatientidApp.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientID"));
	    	colNotesApp.setCellValueFactory(new PropertyValueFactory<Appointment, String>("notes"));
	    	
	    	//Billing Table
	    	//billId.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billID"));
	    	billCost.setCellValueFactory(new PropertyValueFactory<Bill, Double>("Cost"));
	    	appIdBill.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("appID"));
	    	userIdBill.setCellValueFactory(new PropertyValueFactory<Bill, String>("userID"));
	    	patientIdBill.setCellValueFactory(new PropertyValueFactory<Bill, String>("patientID"));
	    	modalityIdBill.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("ModalityID"));
	    	
			//Set Items into all tables
	    	tableViewOrder.setItems(getOrderList());
			//tableViewAppointment.setItems(getAppointmentList()); ADD TO WHEN SELECT FUNCTION
			//tableViewBill.setItems(getBillList()); ADD TO WHEN SELECT FUNCTION
	    	
			/*tableViewOrders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			tableViewAppointment.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			tableViewBill.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);*/

	    }
	    public void onSelectedApp(ActionEvent event) {
	    	
	    }
	    public void onSelectedBill(ActionEvent event) {
	    	
	    }
	    public void unselectedOrder(ActionEvent event) {
	    	
	    }
	    // ObservableList: A list that enables listeners to track changes when they occur
	    // The following  method will return an ObservableList of  object
	    public ObservableList<Order>  getOrderList(){
	    	
	    	ObservableList<Order> order = FXCollections.observableArrayList();

	        String SQLQuery = "SELECT * FROM orders ORDER BY orderID ASC;"; //ADD WHERE idPatient == ''
	       	ResultSet rs = null;

	       	try(
	       			Connection conn = RISDbConfig.getConnection();
	       			PreparedStatement displaybill = conn.prepareStatement(SQLQuery);
	       	){
	       		//displayprofile.setInt(1, cutomerId);
	       		rs = displaybill.executeQuery();
	       		// check to see if receiving any data
	       		while (rs.next()){
	       			order.add(new Order(rs.getString("emergencyLevel").toString(),rs.getString("userID").toString(),rs.getString("patientID").toString(), rs.getInt("modalityID")));
	       		}
	       	}catch(SQLException ex){
	       		RISDbConfig.displayException(ex);
	       		return null;
	       	}finally{
	       		if(rs != null){
	       			//rs.close();
	       		}
	       	}
	        return order;
	    }
	    
	    // ObservableList: A list that enables listeners to track changes when they occur
	    // The following  method will return an ObservableList of  object
	    public ObservableList<Appointment>  getAppointmentList(){
	    	
	    	ObservableList<Appointment> appointment = FXCollections.observableArrayList();

	        String SQLQuery = "SELECT * FROM appointment ORDER BY startTime ASC;"; //ADD WHERE DATE == ''
	       	ResultSet rs = null;

	       	try(
	       			Connection conn = RISDbConfig.getConnection();
	       			PreparedStatement displayappointment = conn.prepareStatement(SQLQuery);
	       	){
	       		//displayprofile.setInt(1, cutomerId);
	       		rs = displayappointment.executeQuery();
	       		// check to see if receiving any data
	       		while (rs.next()){
	       			appointment.add(new Appointment(rs.getString("userID").toString(),rs.getString("patientID").toString(),rs.getInt("modalityID"),rs.getString("startTime").toString(), rs.getString("stopTime").toString()));
	       		}
	       	}catch(SQLException ex){
	       		RISDbConfig.displayException(ex);
	       		return null;
	       	}finally{
	       		if(rs != null){
	       			//rs.close();
	       		}
	       	}
	        return appointment;
	    }
	    
	    // ObservableList: A list that enables listeners to track changes when they occur
	    // The following  method will return an ObservableList of  object
	    public ObservableList<Bill>  getBillList(){
	    	
	    	ObservableList<Bill> bill = FXCollections.observableArrayList();

	    	//idPatient, firstName, lastName, Address, bloodType, age, gender, height, weight, insulinType, phone, idDoctor
	        String SQLQuery = "SELECT * FROM billing ORDER BY billID ASC;"; //ADD WHERE idPatient == ''
	       	ResultSet rs = null;

	       	try(
	       			Connection conn = RISDbConfig.getConnection();
	       			PreparedStatement displaybill = conn.prepareStatement(SQLQuery);
	       	){
	       		//displayprofile.setInt(1, cutomerId);
	       		rs = displaybill.executeQuery();
	       		// check to see if receiving any data
	       		while (rs.next()){
	       			bill.add(new Bill(rs.getDouble("Cost"),rs.getInt("appID"),rs.getString("userID").toString(),rs.getString("patientID").toString(),rs.getInt("modalityID")));
	       		}
	       	}catch(SQLException ex){
	       		RISDbConfig.displayException(ex);
	       		return null;
	       	}finally{
	       		if(rs != null){
	       			//rs.close();
	       		}
	       	}
	        return bill;
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

	

}
