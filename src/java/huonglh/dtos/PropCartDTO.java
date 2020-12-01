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
public class PropCartDTO implements Serializable {

    private String sceneID, sceneName, propID, propName, dateFrom, dateTo;
    int quantities;

    public PropCartDTO(String sceneID, String sceneName, String propID, String propName, String dateFrom, String dateTo, int quantities) {
        this.sceneID = sceneID;
        this.sceneName = sceneName;
        this.propID = propID;
        this.propName = propName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.quantities = quantities;
    }

    public String getSceneID() {
        return sceneID;
    }

    public void setSceneID(String sceneID) {
        this.sceneID = sceneID;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getPropID() {
        return propID;
    }

    public void setPropID(String propID) {
        this.propID = propID;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
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

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

}
