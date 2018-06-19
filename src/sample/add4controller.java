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

public class add4controller  {
    public TextField div;

    sqlController s = new sqlController();







    public void ajout(ActionEvent actionEvent) {
        try {
            s.insertd(div.getText());
        }catch (SQLException r){
            System.out.println(r);
        }
        Stage a =(Stage) div.getScene().getWindow();
        a.close();

    }


    public void close(ActionEvent actionEvent) {

    }


}
