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
public class SceneDTO implements Serializable {

    private String id, name, location, description, dateFrom, dateTo, directorID;
    private int cuts, props;

    public SceneDTO(String id, String name, String location, String dateFrom, String dateTo, String description, int cuts, int props, String directorID) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.directorID = directorID;
        this.cuts = cuts;
        this.props = props;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public int getCuts() {
        return cuts;
    }

    public void setCuts(int cuts) {
        this.cuts = cuts;
    }

    public int getProps() {
        return props;
    }

    public void setProps(int props) {
        this.props = props;
    }

}
