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
public class PropSceneDTO implements Serializable {

    private String id;
    private PropDTO prop;
    private SceneDTO scene;
    private int quantities;
    private String dateFrom, dateTo;

    public PropSceneDTO(String id, PropDTO prop, SceneDTO scene, String dateFrom, String dateTo) {
        this.id = id;
        this.prop = prop;
        this.scene = scene;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PropDTO getProp() {
        return prop;
    }

    public void setProp(PropDTO prop) {
        this.prop = prop;
    }

    public SceneDTO getScene() {
        return scene;
    }

    public void setScene(SceneDTO scene) {
        this.scene = scene;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

}
