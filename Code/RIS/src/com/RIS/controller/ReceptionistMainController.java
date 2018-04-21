package com.RIS.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.RIS.model.Appointment;
import com.RIS.model.Bill;
import com.RIS.model.Order;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReceptionistMainController {

		@FXML private TableView<Order> tableViewOrder;
		@FXML private TableView<Appointment> tableViewAppointment;
		@FXML private TableView<Bill> tableViewBill;
	    @FXML private TableColumn<Order, Integer> colOrderId;
	    @FXML private TableColumn<Order, String> colEmergency, colUserId, colPatientId, colNotesOrder, colModalityorder;
	    @FXML private TableColumn<Appointment, String> colStartTime, colStopTime, colDate, colPatientidApp, colNotesApp;
	    @FXML private TableColumn<Appointment, String> colModality;
	    @FXML private TableColumn<Bill, Integer> billId, appIdBill, modalityIdBill;
	    @FXML private TableColumn<Bill, Double> billCost;
	    @FXML private TableColumn<Bill, String> userIdBill, patientIdBill;
	    @FXML private DatePicker datepicker;
		@FXML private ComboBox<Integer> comboShift = new ComboBox<Integer>(
				FXCollections.observableArrayList(
						  15, 30, 45, 60
		    	 )
		);
	    
	    private String ID;
	    
	    @FXML
	    void initialize() {  	
			//Set Items into all tables
	    	tableViewOrder.setItems(getOrderList());
	    }
	    @FXML
	    public void onSelectedApp() {
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	LocalDate localDate = LocalDate.now();
	    	
			tableViewAppointment.setItems(getAppointmentList(dtf.format(localDate)));
			datepicker.setValue(localDate);
	    }
	    @FXML
	    public void onSelectedBill() {
			tableViewBill.setItems(getBillList());
	    }
	    @FXML
	    public void onSelectedOrder() {
	    	tableViewOrder.setItems(getOrderList());
	    }
	    @FXML
	    public void shiftSchedule(ActionEvent event) {
	    	comboShift.getValue();
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	//Query to move all appointments in that day
	    	
	    	//And Appointment are past last 
	    	String query = "UPDATE Appointment SET startTime = startTime + ?  WHERE date = ?  AND stopTime > ?;";

        	try(
        	    Connection conn = RISDbConfig.getConnection();
        	    PreparedStatement updateapps = conn.prepareStatement(query);
        	){
        		updateapps.setString(1, comboShift.getValue()+"");
        		updateapps.setString(2, datepicker.getValue()+"");
        		updateapps.setString(3, "CURRENT TIME");
        		
        		updateapps.executeUpdate();
        	} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);

    		}
	    }
	    @FXML
	    public void refreshApps(ActionEvent event) {
	    	
	    	if(datepicker.getValue() != null) {
		    	LocalDate localdate = datepicker.getValue();
		    	tableViewOrder.setItems(getOrderList());
				tableViewAppointment.setItems(getAppointmentList(localdate.toString())); 
	    	}
	    	else {
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	LocalDate localDate = LocalDate.now();
		    	
				tableViewAppointment.setItems(getAppointmentList(dtf.format(localDate)));
	    	}
	    }
	    // ObservableList: A list that enables listeners to track changes when they occur
	    // The following  method will return an ObservableList of  object
	    public ObservableList<Order>  getOrderList(){
	    	
	        //set up the columns in the tables
	    	//Order Table
	    	colEmergency.setCellValueFactory(new PropertyValueFactory<Order, String>("emergencyLevel"));
	    	colUserId.setCellValueFactory(new PropertyValueFactory<Order, String>("userId"));
	    	colPatientId.setCellValueFactory(new PropertyValueFactory<Order, String>("patientId"));
	    	colModalityorder.setCellValueFactory(new PropertyValueFactory<Order, String>("modality"));
	    	colNotesOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("notes"));
	    	
			tableViewOrder.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    	
	    	ObservableList<Order> order = FXCollections.observableArrayList();
	        String SQLQuery = "SELECT * FROM orders ORDER BY emergencyLevel;"; //ADD WHERE idPatient == ''
	       	ResultSet rs = null;

	       	try(
	       			Connection conn = RISDbConfig.getConnection();
	       			PreparedStatement displaybill = conn.prepareStatement(SQLQuery);
	       	){
	       		//displayprofile.setInt(1, cutomerId);
	       		rs = displaybill.executeQuery();
	       		// check to see if receiving any data
	       		while (rs.next()){
	       			order.add(new Order(rs.getString("emergencyLevel").toString(),rs.getString("userID").toString(),rs.getString("patientID").toString(), rs.getString("modality"), rs.getString("notes")));
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
	    public ObservableList<Appointment>  getAppointmentList(String date){
	    	
	    	//Appointment Table
	    	colDate.setCellValueFactory(new PropertyValueFactory<Appointment, String>("date"));
	    	colStartTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("startTimeToString"));
	    	colStopTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("stopTimeToString"));
	    	colModality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modalityId"));
	    	colPatientidApp.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientId"));
	    	colNotesApp.setCellValueFactory(new PropertyValueFactory<Appointment, String>("notes"));
	    	
	    	tableViewAppointment.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    	
	    	ObservableList<Appointment> appointment = FXCollections.observableArrayList();

	        String SQLQuery = "SELECT * FROM appointment WHERE date = ? ORDER BY startTime ASC;";
	       	ResultSet rs = null;

	       	try(
	       			Connection conn = RISDbConfig.getConnection();
	       			PreparedStatement displayappointment = conn.prepareStatement(SQLQuery);
	       	){
	       		displayappointment.setString(1, date);
	       		rs = displayappointment.executeQuery();
	       		while (rs.next()){
	       			
	       			appointment.add(new Appointment(rs.getString("patientID").toString(),rs.getInt("modalityID"),rs.getString("date"), rs.getInt("startTime"), rs.getInt("stopTime"), rs.getString("notes").toString()));
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
	    	
	    	//Billing Table
	    	billCost.setCellValueFactory(new PropertyValueFactory<Bill, Double>("Cost"));
	    	appIdBill.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("appId"));
	    	userIdBill.setCellValueFactory(new PropertyValueFactory<Bill, String>("userId"));
	    	patientIdBill.setCellValueFactory(new PropertyValueFactory<Bill, String>("patientId"));
	    	modalityIdBill.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("ModalityId"));
	    	
	    	tableViewBill.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    	
	    	ObservableList<Bill> bill = FXCollections.observableArrayList();

	        String SQLQuery = "SELECT * FROM bills ORDER BY billID ASC;"; //ADD WHERE idPatient == ''
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
	    void NewAppfromOrder(ActionEvent event) throws IOException {
	    	
	        ObservableList<Order> selectedRows;

	        //this gives us the rows that were selected
	        selectedRows = tableViewOrder.getSelectionModel().getSelectedItems();
	        
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/AddNewAppointment.fxml"));
	    	Parent root = (Parent) loader.load();
	        AddNewAppointmentController controller = loader.getController();
	        controller.setID(selectedRows.get(0).getPatientId());
	        controller.setNotes(selectedRows.get(0).getNotes());
	        controller.setUserID(selectedRows.get(0).getUserId());
	        controller.setModality(selectedRows.get(0).getModality());
	        
	        Stage stage = new Stage();
	        stage.setTitle("RIS");
	        stage.setScene(new Scene (root));
	        stage.show();
	       
	    }
	    @FXML
	    void deleteAppointment(ActionEvent event) throws IOException {
	    	
	        ObservableList<Appointment> selectedRows, appointment;
	        appointment = tableViewAppointment.getItems();

	        //this gives us the rows that were selected
	        selectedRows = tableViewAppointment.getSelectionModel().getSelectedItems();

	        //loop over the selected rows and remove the Patient objects from the table
	        for (Appointment app: selectedRows)
	        {
	        	appointment.remove(app);
	        	String query = "DELETE FROM Appointment where patientId = ?;";

	        	try(
	        	    Connection conn = RISDbConfig.getConnection();
	        	    PreparedStatement updateprofile = conn.prepareStatement(query);
	        	){
	        		updateprofile.setString(1, app.getUserId());
	        	    updateprofile.executeUpdate();
	        	} catch (Exception e) {
	    			System.out.println("Status: operation failed due to "+e);

	    		}
	        }
	       
	    }
	    @FXML
	    void editAppointment(ActionEvent event) throws IOException {
	    	
	        ObservableList<Appointment> selectedRows;

	        //this gives us the rows that were selected
	        selectedRows = tableViewAppointment.getSelectionModel().getSelectedItems();
	        
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/AddNewAppointment.fxml"));
	    	Parent root = (Parent) loader.load();
	        AddNewAppointmentController controller = loader.getController();
	        controller.setID(selectedRows.get(0).getPatientId());
	        controller.setNotes(selectedRows.get(0).getNotes());
	        controller.setUserID(selectedRows.get(0).getUserId());
	        controller.setModalityId(selectedRows.get(0).getModalityId());
	        controller.setDate(selectedRows.get(0).getDate());
	        controller.setStartTime(selectedRows.get(0).getStartTime());
	        controller.setStopTime(selectedRows.get(0).getStopTime());
	        
	        Stage stage = new Stage();
	        stage.setTitle("RIS");
	        stage.setScene(new Scene (root));
	        stage.show();
	       
	    }
		public void setID(String text) {
			// TODO Auto-generated method stub
			this.ID = text;
		}
}
