package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class demende {
    private int id;
    private String nom_d;
    private String nom_s;
    private String nom_p;
    private int count;
    private String date;
    private ImageView image;

    public demende(String nom_d, String nom_s, String nom_p, int count, String date,int id) {
        this.id=id;
        this.nom_d = nom_d;
        this.nom_s = nom_s;
        this.nom_p = nom_p;
        this.count = count;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getNom_d() {
        return nom_d;
    }

    public void setNom_d(String nom_d) {
        this.nom_d = nom_d;
    }

    public String getNom_s() {
        return nom_s;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
