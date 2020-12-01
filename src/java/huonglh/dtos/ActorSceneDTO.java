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
public class ActorSceneDTO implements Serializable {

    private String id;
    private SceneDTO scene;
    private ActorDTO actor;
    private String role, script, sceneName, date;

    public ActorSceneDTO(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SceneDTO getScene() {
        return scene;
    }

    public void setScene(SceneDTO scene) {
        this.scene = scene;
    }

    public ActorDTO getActor() {
        return actor;
    }

    public void setActor(ActorDTO actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
