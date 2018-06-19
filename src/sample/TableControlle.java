package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class TableControlle implements Initializable {
    public TableView<demende> demende;
    public TableColumn<demende, String> nom_d;
    public TableColumn<demende, String> nom_s;
    public TableColumn<demende, String> date;

    public TableColumn<demende, Integer> id;

    public TreeView tree;
    public Button add;

    public Desktop desktop = Desktop.getDesktop();


    ObservableList<demende> list = FXCollections.observableArrayList();
    sqlController s = new sqlController();
    ObservableList<String> b = FXCollections.observableArrayList();
    ObservableList<String> d = FXCollections.observableArrayList();

    public demende getitem() {
        demende a = demende.getSelectionModel().getSelectedItem();
        return a;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<String> root= new TreeItem<>("Devisions");
        root.setExpanded(true);
        try {
            for (String a:s.tree(b)) {
                System.out.println(a);
                TreeItem<String> item = new TreeItem<String> (a);
                root.getChildren().add(item);
                for (String b:s.tree2(d,a)) {
                    TreeItem<String> item1 = new TreeItem<String> (b);
                    item.getChildren().add(item1);
                }
                d.clear();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        tree.setRoot(root);

        nom_d.setCellValueFactory(new PropertyValueFactory<demende, String>("nom_d"));
        date.setCellValueFactory(new PropertyValueFactory<demende, String>("date"));
        nom_s.setCellValueFactory(new PropertyValueFactory<demende, String>("nom_s"));
        id.setCellValueFactory(new PropertyValueFactory<demende,Integer>("id"));
        id.setVisible(false);
        try {
            demende.setItems(s.initable(list));

        } catch (SQLException e) {
            e.printStackTrace();
        }

/*

        nom_d.setCellValueFactory(new PropertyValueFactory<demende, String>("nom_d"));
        nom_p.setCellValueFactory(new PropertyValueFactory<demende, String>("nom_p"));
        date.setCellValueFactory(new PropertyValueFactory<demende, DatePicker>("date"));
        count.setCellValueFactory(new PropertyValueFactory<demende, Integer>("count"));
        nom_s.setCellValueFactory(new PropertyValueFactory<demende, String>("nom_s"));

        try {
            demende.setItems(s.initable(list, String.valueOf(date1.getValue())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

*/
    }
    public void add(ActionEvent actionEvent) throws IOException {
        Parent window = FXMLLoader.load(getClass().getResource("add2.fxml"));
        Scene badro = new Scene(window);
        Stage st1 = new Stage();
        st1.initModality(Modality.APPLICATION_MODAL);
        st1.initOwner(demende.getScene().getWindow());
        st1.setTitle("ajoute");
        st1.setScene(badro);
        st1.showAndWait();
        ref();

    }

    public void ref_tree(){
        b.clear();
        d.clear();
        TreeItem<String> root= new TreeItem<>("Devisions");
        root.setExpanded(true);
        try {
            for (String a:s.tree(b)) {
                System.out.println(a);
                TreeItem<String> item = new TreeItem<String> (a);
                root.getChildren().add(item);
                for (String b:s.tree2(d,a)) {
                    TreeItem<String> item1 = new TreeItem<String> (b);
                    item.getChildren().add(item1);
                }
                d.clear();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        tree.setRoot(root);

    }
    public void ref() {


        list.clear();
        try {
            demende.setItems(s.initable(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void click(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            list.clear();
            ObservableList<TreeItem> d = tree.getSelectionModel().getSelectedItems();
            for (TreeItem a : d) {
                try {
                    if (String.valueOf(a.getValue()).equals("Devisions")){
                        demende.setItems(s.initable(list));
                    }else{
                    demende.setItems(s.click2(list,String.valueOf(a.getValue())));}
                    if (list.isEmpty()){
                        demende.setItems(s.click(list,String.valueOf(a.getValue())));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }





    public void show(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2) {
            FXMLLoader loader =new  FXMLLoader(getClass().getResource("image.fxml"));
            Parent window = loader.load();
            Scene badro = new Scene(window);
            Stage st1 = new Stage();
            imagecontroller k = loader.getController();
            try {
                k.myf(getitem().getId());
                st1.initModality(Modality.APPLICATION_MODAL);
                st1.initOwner(demende.getScene().getWindow());
                st1.setTitle("image");
                st1.setScene(badro);
                st1.show();
                ref();
            }catch (Exception e){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Erruer");
                alert1.setHeaderText(null);
                alert1.setContentText("Please select element");
                alert1.showAndWait();
            }

        }
    }


    public void add3(ActionEvent actionEvent) throws IOException {
        Parent window = FXMLLoader.load(getClass().getResource("add3.fxml"));
        Scene badro = new Scene(window);
        Stage st1 = new Stage();
        st1.initModality(Modality.APPLICATION_MODAL);
        st1.initOwner(demende.getScene().getWindow());
        st1.setTitle("ajoute");
        st1.setScene(badro);
        st1.showAndWait();
        ref_tree();
    }

    public void supp(ActionEvent actionEvent) {
        demende a = demende.getSelectionModel().getSelectedItem();
        int id = a.getId();
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Erruer");
        alert1.setHeaderText(null);
        alert1.setContentText("Vouler-vous suprimer cette demende ?");
        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                s.supp(id);
                ref();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {

        }

    }

    public void add4(ActionEvent actionEvent) throws IOException {
        Parent window = FXMLLoader.load(getClass().getResource("add4.fxml"));
        Scene badro = new Scene(window);
        Stage st1 = new Stage();
        st1.initModality(Modality.APPLICATION_MODAL);
        st1.initOwner(demende.getScene().getWindow());
        st1.setTitle("ajoute");
        st1.setScene(badro);
        st1.showAndWait();
        ref_tree();
    }
}