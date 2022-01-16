package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCategories;
    private String nameCategories;

    public Categories() {
    }

    public Categories(int idCategories, String nameCategories) {
        this.idCategories = idCategories;
        this.nameCategories = nameCategories;
    }

    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    public String getNameCategories() {
        return nameCategories;
    }

    public void setNameCategories(String nameCategories) {
        this.nameCategories = nameCategories;
    }
}
