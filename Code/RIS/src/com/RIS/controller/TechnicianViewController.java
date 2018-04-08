package com.RIS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TechnicianViewController {

    @FXML
    private TableView<?> tachTable;
    
    

    @FXML
    private TableColumn<?, ?> col1;

    @FXML
    private TableColumn<?, ?> col2;

    @FXML
    private TableColumn<?, ?> col3;

    @FXML
    private TableColumn<?, ?> col4;

    @FXML
    private TableColumn<?, ?> col5;

    @FXML
    private TableColumn<?, ?> col6;

    @FXML
    private TextField techNotesTextArea;

    @FXML
    private Text datetimeText;

    @FXML
    private Text procedureText;

    @FXML
    private Text reasonText;

    @FXML
    private Text referringText;

    @FXML
    private Text priorityText;

    @FXML
    void browse(ActionEvent event) {

    }

    @FXML
    void displayAppointments(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

}
