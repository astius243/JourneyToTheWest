/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.dtos;

import java.io.Serializable;

/**
 *
 * @author Hau Huong
 */
public class PropDTO implements Serializable {

    private String id, name, image, description;
    private int quantities;
    private boolean available;

    public PropDTO(String id, String name, String image, String description, int quantities, boolean available) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.quantities = quantities;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}