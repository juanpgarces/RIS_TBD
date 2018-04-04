package com.RIS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNewAppointment {

    @FXML private DatePicker txtDate; 
    @FXML private TextField txtTime, txtFirstName, txtLastName, txtId;
    @FXML private TextArea txtNotes;
    @FXML private ComboBox<?> comboModality;
    
    public void createAppointment(ActionEvent event){
    	
    	
    }	
}