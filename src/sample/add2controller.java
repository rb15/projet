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

public class add2controller implements Initializable {
    public ComboBox div;
    public ComboBox serv;
    public Label path;
    public FileChooser fe;
    public File f;
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
    public void load(ActionEvent actionEvent) {
        fe = new FileChooser();
        fe.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.jpg","*.jpeg","*.png"));
        f = fe.showOpenDialog(null);
        if (f!=null)
            path.setText(f.getAbsolutePath());

    }

    public void divv(ActionEvent actionEvent) {
        b.clear();
        String k = String.valueOf(div.getValue());
        try {
            serv.setItems(s.setserv(b,k));
        }catch (SQLException e){
            System.out.println(e);
        }


    }

    public void ajout(ActionEvent actionEvent) {
        final Calendar cal = Calendar.getInstance();
        try {
            s.insert(String.valueOf(div.getValue()),String.valueOf(serv.getValue()),dateFormat.format(cal.getTime()),f);

        }catch (SQLException r){
            System.out.println(r);
        }
        Stage a =(Stage) div.getScene().getWindow();
        a.close();}


    public void close(ActionEvent actionEvent) {
    }


}
