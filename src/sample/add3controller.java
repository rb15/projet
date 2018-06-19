package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class add3controller implements Initializable {
    public ComboBox div;
    public TextField serv;

    sqlController s = new sqlController();
    ObservableList<String> a = FXCollections.observableArrayList();
    ObservableList<String> b = FXCollections.observableArrayList();
    ObservableList<String> c = FXCollections.observableArrayList();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            div.setItems(s.setdiv(a));
        }catch (SQLException e){
            System.out.println(e);
        }



    }






    public void ajout(ActionEvent actionEvent) {
        try {
            s.inserts(serv.getText(),String.valueOf(div.getValue()));
        }catch (SQLException r){
            System.out.println(r);
        }
        Stage a =(Stage) div.getScene().getWindow();
        a.close();

    }


    public void close(ActionEvent actionEvent) {

    }


}
