package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.RIS.model.Appointment;
import com.RIS.model.Bill;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class TechnicianViewController {

	@FXML private ImageView pacsViewer;
    @FXML private TableView<Appointment> techTable;
    @FXML private TableColumn<Appointment, Integer> colPatientID;
    @FXML private TableColumn<Appointment, String> colTime, colModality, colEmergencyLevel, colUserID;
    @FXML private TextField textAreaTechNotes;
    @FXML private Text txtNotes;
    @FXML private Label txtReady;  
    File stored;
    private String ID;
    
    @FXML
    void initialize() { 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate localDate = LocalDate.now();
    	
		techTable.setItems(getAppointmentList(dtf.format(localDate)));
    }

    public ObservableList<Appointment>  getAppointmentList(String date){
    	
    	//Appointment Table
    	colTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("startTimeToString"));
    	colPatientID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("patientId"));
    	//colUserID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
    	colUserID.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userId"));
    	colModality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modName"));
 
    	techTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	ObservableList<Appointment> appointment = FXCollections.observableArrayList();

        String SQLQuery = "SELECT * FROM appointment WHERE date = ? AND status='new' ORDER BY startTime ASC;";
       	ResultSet rs = null;

       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement displayappointment = conn.prepareStatement(SQLQuery);
       	){
       		displayappointment.setString(1, date);
       		rs = displayappointment.executeQuery();
       		
       		while (rs.next()){
       			appointment.add(new Appointment(rs.getInt("appID"), rs.getString("userID"),rs.getString("patientID").toString(),rs.getInt("modalityID"),rs.getString("date"), rs.getInt("startTime"), rs.getInt("stopTime"), rs.getString("notes").toString()));
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
    @FXML
    void submit(ActionEvent event) {
    	
    	// sets appointment = 'pending' and appends technician notes to physician notes	
    	ObservableList<Appointment> selectedRows;
    	selectedRows = techTable.getSelectionModel().getSelectedItems();
    	
    	String allNotes = "Physician Notes:\n" + selectedRows.get(0).getNotes()
    			+ "\nTechnician Notes:\n" + textAreaTechNotes.getText();
    	
    	String query = "UPDATE appointment SET status = 'pending', notes= ? WHERE appID = ?;";
		try (Connection conn = RISDbConfig.getConnection();
		PreparedStatement updateApp = conn.prepareStatement(query);) {
			
			updateApp.setString(1, allNotes);
			updateApp.setInt(2, selectedRows.get(0).getAppointmentId());
			System.out.println( selectedRows.get(0).getAppointmentId());
			
			updateApp.execute();

		} catch (Exception e) {
			System.out.println("Status: operation failed due to "+e);
			
			}
		txtReady.setText("Images Have been Submitted Successfully!");
    	createBill();
    }
    
    //creates bill
    public void createBill(){
    	double cost = 0.0;
    	
    	ObservableList<Appointment> selectedRows;
    	
    	selectedRows = techTable.getSelectionModel().getSelectedItems();
    	String query = "SELECT cost FROM modality WHERE modID = ?;";
    	
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
	    		st.setInt(1, selectedRows.get(0).getModalityId());
	    		ResultSet rs = st.executeQuery();
	    		
			if(rs.next())
				cost = rs.getDouble("cost");
			
			} catch (Exception e) {
			System.out.println("Status: operation failed due to "+e);
			}
	
        	Bill newBill = new Bill(
        			cost,
        			selectedRows.get(0).getAppointmentId(),
        			selectedRows.get(0).getUserId(),
        			selectedRows.get(0).getPatientId(),
        			selectedRows.get(0).getModalityId()
        			);
        	
        	query = "INSERT INTO bills (Cost, AppID, userID, patientID, modalityID) VALUES (?,?,?,?,?)";
        	try (Connection conn = RISDbConfig.getConnection();
        			PreparedStatement st = conn.prepareStatement(query);) {
        		
    	    		st.setDouble(1, cost);
    	    		st.setInt(2, selectedRows.get(0).getAppointmentId());
    	    		st.setString(3, selectedRows.get(0).getUserId());
    	    		st.setString(4, selectedRows.get(0).getPatientId());
    	    		st.setInt(5, selectedRows.get(0).getModalityId());
    	    		
    	    		st.executeUpdate();
    	    		
    			} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    		}
    }

    @FXML void imageSelect(ActionEvent event) {
    	//Select Image
    	FileChooser fileChooser = new FileChooser();
    	File file = fileChooser.showOpenDialog(null);
    	if(file != null) {
    		openFile(file);
    	}
		txtReady.setText("Ready to be uploaded...");
    }
    public void openFile(File file) {
    	try {	
    		File destination = new File("src/com/RIS/images/"+file.getName());
    		
    		Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    		BufferedImage bimage = ImageIO.read(destination);
    		
    		Image image = SwingFXUtils.toFXImage(bimage, null);
    		
    		pacsViewer.setImage(image);
    		stored = destination;
    		///saveImage(destination);
    		
    	} catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }
    
    @FXML void upload(ActionEvent event) {
    	//Save Image
    	if (stored != null) {
    		try {
    			saveImage(stored);
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    		txtReady.setText("File has been Uploaded Succesfully.");
    	}   	    	
    }
    
    public void saveImage(File file) throws IOException {
    	
        ObservableList<Appointment> selectedRows;
    	selectedRows = techTable.getSelectionModel().getSelectedItems();
        
        String query = "INSERT INTO pacs (image, appointment_appID, appointment_userID, appointment_patientID, appointment_modalityID)" + "VALUES (?,?,?,?,?)";
        
        try (Connection conn = RISDbConfig.getConnection();
	       			PreparedStatement st = conn.prepareStatement(query);){
         if (file != null) {
        	 
        	 st.setString(1, file.getPath());
        	 st.setInt(2, selectedRows.get(0).getAppointmentId());
        	 st.setString(3, selectedRows.get(0).getUserId());
        	 st.setString(4, selectedRows.get(0).getPatientId());
        	 st.setInt(5, selectedRows.get(0).getModalityId());
        	       
	         st.executeUpdate();
         }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	System.out.println("Status: operation failed due to "+e);
		}    
    }   	 
    // Loads physician's notes when row is selected
    @FXML
    void loadNotes(ActionEvent event){
    	
    	 ObservableList<Appointment> selectedRows;

         //this gives us the rows that were selected
         selectedRows = techTable.getSelectionModel().getSelectedItems();
         
         if(!selectedRows.isEmpty()) {
	         txtNotes.setText(selectedRows.get(0).getNotes());
	         String SQLQuery = "SELECT * FROM appointment WHERE appID = ?;";
	        	ResultSet rs = null;
	
	        	try(
	        			Connection conn = RISDbConfig.getConnection();
	        			PreparedStatement displaypatient = conn.prepareStatement(SQLQuery);
	        	){
	        		displaypatient.setString(1, selectedRows.get(0).getPatientId());
	        		
	        		rs = displaypatient.executeQuery();
	        		// check to see if receiving any data
	        		Appointment newApp = new Appointment(rs.getString("userID"),rs.getString("patientID").toString(),rs.getInt("modalityID"),rs.getString("date"), rs.getInt("startTime"), rs.getInt("stopTime"), rs.getString("notes").toString());
	        		
	        		//Load Appointment Info
	             txtNotes.setText(newApp.getNotes());
	             
	           	}catch(SQLException ex){
	           		RISDbConfig.displayException(ex);
	           	}
         }
    }
  
    

	public void setID(String text) {
		// TODO Auto-generated method stub
		this.ID = text;
	}

}
