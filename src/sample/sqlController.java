package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.text.TableView;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class sqlController {
    Connection cnn;

    public sqlController() {
        sqlcnx c = new sqlcnx();
        cnn = c.cnx();

        if (cnn == null) System.exit(1);

    }

    public boolean iscnn() {
        try {
            return !cnn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public InputStream imagereturn(int a) throws SQLException {
        InputStream inp=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select image from demende where id_de=? ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setInt(1,a);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                inp = resultSet.getBinaryStream("image");
            }
            return inp;
            } catch(Exception e){
                System.out.println(e);
                return null;
            }finally{
                preparedStatement.close();
                resultSet.close();
            }

        }



    public ObservableList<demende> initable(ObservableList<demende> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from demende order by date desc";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new demende(
                        resultSet.getString("nom_d"),
                        resultSet.getString("nom_s"),
                        resultSet.getString("nom_p"),
                        resultSet.getInt("count"),
                        resultSet.getString("date"),
                        resultSet.getInt("id_de")
                ));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public ObservableList<String> setdiv(ObservableList<String> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select nom_d from devision";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("nom_d"));
              a.add(resultSet.getString("nom_d"));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }



    public ObservableList<String> setserv(ObservableList<String> a,String b) throws SQLException {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet= null;
            String query ="select nom_s from service where nom_d=?";
            try {
                preparedStatement = cnn.prepareStatement(query);
                preparedStatement.setString(1,b);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    System.out.println(resultSet.getString("nom_s"));
                  a.add(resultSet.getString("nom_s"));
                }

                return a;

            } catch (Exception e) {
                System.out.println(e);
                return a;
            }finally {
                preparedStatement.close();
                resultSet.close();
            }
        }

    public void insert(String a, String b, String d, File f)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO demende (nom_d,nom_s,date,image)VALUES(?,?,?,?)";
        try {
            FileInputStream fis = new FileInputStream(f);
            preparedStatement = cnn.prepareStatement(query);
            System.out.println(d);
            preparedStatement.setString(1,a);
            preparedStatement.setString(2,b);
            preparedStatement.setString(3,d);
            preparedStatement.setBinaryStream(4,fis,(int)f.length());


            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not added");

        }
        finally {
            preparedStatement.close();
        }
    }

public void inserts(String a, String b)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO service (nom_s,nom_d)VALUES(?,?)";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            preparedStatement.setString(2,b);


            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not added");

        }
        finally {
            preparedStatement.close();
        }
    }

    public void insertd(String a)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO devision (nom_d)VALUES(?)";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);



            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not added");

        }
        finally {
            preparedStatement.close();
        }
    }
    public void supp(int a)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="delete from demende where id_de=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setInt(1,a);

            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("non suprimer");

        }
        finally {
            preparedStatement.close();
        }
    }


    public ObservableList<String> tree(ObservableList<String> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select nom_d from devision ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("nom_d"));
                a.add(resultSet.getString("nom_d"));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public ObservableList<String> tree2(ObservableList<String> a,String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select nom_s from service where nom_d=? ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("nom_s"));
                a.add(resultSet.getString("nom_s"));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public ObservableList<demende> click(ObservableList<demende> a,String b) throws SQLException {
        a.clear();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from demende where nom_s=? order by date desc ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new demende(
                        resultSet.getString("nom_d"),
                        resultSet.getString("nom_s"),
                        resultSet.getString("nom_p"),
                        resultSet.getInt("count"),
                        resultSet.getString("date"),
                        resultSet.getInt("id_de")
                ));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

public ObservableList<demende> click2(ObservableList<demende> a,String b) throws SQLException {
        a.clear();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from demende where nom_d=? order by date desc ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new demende(
                        resultSet.getString("nom_d"),
                        resultSet.getString("nom_s"),
                        resultSet.getString("nom_p"),
                        resultSet.getInt("count"),
                        resultSet.getString("date"),
                        resultSet.getInt("id_de")
                ));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }






}

