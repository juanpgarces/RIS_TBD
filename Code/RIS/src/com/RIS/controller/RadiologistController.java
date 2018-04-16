package com.RIS.controller;

import com.RIS.model.Appointment;
import com.RIS.model.PACS;
import com.RIS.model.Transcript;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RadiologistController {

    @FXML private TableView<Appointment> tableViewApp;
    @FXML private TableView<PACS> tableViewPacs;

    @FXML private TableColumn<PACS, Integer> colImageId;
    @FXML private TableColumn<Appointment, String> colPatientId;
    @FXML private TableColumn<Appointment, String> colModality;
    @FXML private TableColumn<Appointment, String> colDate;

    @FXML private Label lblDOB, lblLastName, lblFirstName, lblPatientId, lblNotesPatient, lblGender, lblNotesApp, lblStopTime, lblStartTime, lblDate, lblModality;
    private String ID;
    
    @FXML
    void loadImages(ActionEvent event) {

    }

    @FXML
    void refreshApp(ActionEvent event) {

    }

    @FXML
    void submitReport(ActionEvent event) {

    }

}
