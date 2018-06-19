package sample;

import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;

public class imagecontroller {
    sqlController s= new sqlController();
    public Pane pane;

    public void myf(int a){
        InputStream inp=null;

        {
            try {
                inp = s.imagereturn(a);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            OutputStream os = null;
            try {
                os = new FileOutputStream( new File("photo.jpg"));

                byte[] content = new byte[1024];
                int size = 0;
                while((size = inp.read(content)) != -1){
                    os.write(content, 0, size);
                }

                os.close();
                inp.close();
                Image image = new Image("file:photo.jpg");
               ImageView imageView = new ImageView(image);
               pane.getChildren().add(imageView);
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
