/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.daos;

import huonglh.db.MyConnection;
import huonglh.dtos.ActorDTO;
import huonglh.dtos.ActorSceneDTO;
import huonglh.dtos.DirectorDTO;
import huonglh.dtos.PropCartDTO;
import huonglh.dtos.PropDTO;
import huonglh.dtos.PropSceneDTO;
import huonglh.dtos.SceneDTO;
import huonglh.dtos.RegistrationDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hau Huong
 */
public class MainDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public RegistrationDTO checkLogin(String username, String password) throws Exception {
        RegistrationDTO dto = null;
        try {
            String sql = "Select Fullname, Role From tblLogin Where Username = ? and Password = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String role = rs.getString("Role");
                dto = new RegistrationDTO(username, fullname, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean insertLogin(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblLogin(Username, Password, Fullname, Role) Values(?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getFullname());
            preStm.setString(4, dto.getRole());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateLogin(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblLogin Set Password = ?, Fullname = ? Where Username = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPassword());
            preStm.setString(2, dto.getFullname());
            preStm.setString(3, dto.getUsername());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteLogin(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblLogin Where Username = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public ActorDTO findActorByUsername(String username) throws Exception {
        ActorDTO actor = null;
        try {
            String sql = "Select ActorID, Fullname, Phone, Email, Image, Description From tblActors Where Username = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                actor = new ActorDTO(rs.getString("ActorID"), username, rs.getString("Fullname"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Image"), rs.getString("Description"));
            }
        } finally {
            closeConnection();
        }
        return actor;
    }

    public ActorDTO findActorByPrimaryKey(String id) throws Exception {
        ActorDTO actor = null;
        try {
            String sql = "Select Username, Fullname, Phone, Email, Image, Description From tblActors Where ActorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                actor = new ActorDTO(id, rs.getString("Username"), rs.getString("Fullname"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Image"), rs.getString("Description"));
            }
        } finally {
            closeConnection();
        }
        return actor;
    }

    public boolean insertActor(ActorDTO actor) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblActors(ActorID, Username, Fullname, Phone, Email, Image, Description) Values(?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actor.getId());
            preStm.setString(2, actor.getUsername());
            preStm.setString(3, actor.getFullname());
            preStm.setString(4, actor.getPhone());
            preStm.setString(5, actor.getEmail());
            preStm.setString(6, actor.getImage());
            preStm.setString(7, actor.getDescription());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateActor(ActorDTO actor) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblActors Set Fullname = ?, Phone = ?,  Email = ?, Image = ?, Description = ? Where ActorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actor.getFullname());
            preStm.setString(2, actor.getPhone());
            preStm.setString(3, actor.getEmail());
            preStm.setString(4, actor.getImage());
            preStm.setString(5, actor.getDescription());
            preStm.setString(6, actor.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteActor(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblActors Where ActorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public DirectorDTO findDirectorByPrimaryKey(String id) throws Exception {
        DirectorDTO director = null;
        try {
            String sql = "Select Username, Fullname, "
                    + "Phone, Email From tblDirectors Where DirectorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                director = new DirectorDTO(id, rs.getString("Username"), rs.getString("Fullname"), rs.getString("Phone"), rs.getString("Email"));
            }
        } finally {
            closeConnection();
        }
        return director;
    }

    public DirectorDTO findDirectorByUsername(String username) throws Exception {
        DirectorDTO director = null;
        try {
            String sql = "Select DirectorID, Fullname, "
                    + "Phone, Email From tblDirectors Where Username = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                director = new DirectorDTO(rs.getString("DirectorID"), username, rs.getString("Fullname"), rs.getString("Phone"), rs.getString("Email"));
            }
        } finally {
            closeConnection();
        }
        return director;
    }

    public boolean insertDirector(DirectorDTO director) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblDirectors(DirectorID, Username, Fullname, Phone, Email) Values(?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, director.getId());
            preStm.setString(2, director.getUsername());
            preStm.setString(3, director.getFullname());
            preStm.setString(4, director.getPhone());
            preStm.setString(5, director.getEmail());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateDirector(DirectorDTO director) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblDirectors Set Fullname = ?, Phone = ?,  Email = ? Where DirectorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, director.getFullname());
            preStm.setString(2, director.getPhone());
            preStm.setString(3, director.getEmail());
            preStm.setString(4, director.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteDirector(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblDirectors Where DirectorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public SceneDTO findSceneByPrimaryKey(String id) throws Exception {
        SceneDTO scene = null;
        try {
            String sql = "Select SceneName, Location, DateFrom, DateTo, "
                    + "Description, TotalCuts, TotalProps, DirectorID From tblScenes Where SceneID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                scene = new SceneDTO(id, rs.getString("SceneName"), rs.getString("Location"), rs.getString("DateFrom"),
                        rs.getString("DateTo"), rs.getString("Description"), rs.getInt("TotalCuts"), rs.getInt("TotalProps"), rs.getString("DirectorID"));
            }
        } finally {
            closeConnection();
        }
        return scene;
    }

    public boolean insertScene(SceneDTO scene) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblScenes(SceneID, SceneName, Location, DateFrom, DateTo,"
                    + " TotalCuts, TotalProps, Description, DirectorID) Values(?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, scene.getId());
            preStm.setString(2, scene.getName());
            preStm.setString(3, scene.getLocation());
            java.sql.Date sqlDate = java.sql.Date.valueOf(scene.getDateFrom());
            preStm.setDate(4, sqlDate);
            sqlDate = java.sql.Date.valueOf(scene.getDateTo());
            preStm.setDate(5, sqlDate);
            preStm.setInt(6, scene.getCuts());
            preStm.setInt(7, 0);
            preStm.setString(8, scene.getDescription());
            preStm.setString(9, scene.getDirectorID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateScene(SceneDTO scene) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblScenes Set SceneName = ?, Location = ?, DateFrom = ?, DateTo = ?, "
                    + "TotalCuts = ?, TotalProps = ?, Description = ?, DirectorID = ? Where SceneID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, scene.getName());
            preStm.setString(2, scene.getLocation());
            preStm.setDate(3, java.sql.Date.valueOf(scene.getDateFrom()));
            preStm.setDate(4, java.sql.Date.valueOf(scene.getDateTo()));
            preStm.setInt(5, scene.getCuts());
            preStm.setInt(6, scene.getProps());
            preStm.setString(7, scene.getDescription());
            preStm.setString(8, scene.getDirectorID());
            preStm.setString(9, scene.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteScene(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblScenes Where SceneID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public PropDTO findPropByPrimaryKey(String id) throws Exception {
        PropDTO prop = null;
        try {
            String sql = "Select PropID, PropName, Image, Quantities, Available, Description From tblProps Where PropID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                prop = new PropDTO(id, rs.getString("PropName"), rs.getString("Image"),
                        rs.getString("Description"), rs.getInt("Quantities"), rs.getBoolean("Available"));
            }
        } finally {
            closeConnection();
        }
        return prop;
    }

    public boolean insertProp(PropDTO prop) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblProps(PropID, PropName, Image, "
                    + "Quantities, Available, Description) Values(?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, prop.getId());
            preStm.setString(2, prop.getName());
            preStm.setString(3, prop.getImage());
            preStm.setInt(4, prop.getQuantities());
            preStm.setBoolean(5, prop.isAvailable());
            preStm.setString(6, prop.getDescription());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateProp(PropDTO prop) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblProps Set PropName = ?, Image = ?, "
                    + "Quantities = ?, Available = ?, Description = ? Where PropID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, prop.getName());
            preStm.setString(2, prop.getImage());
            preStm.setInt(3, prop.getQuantities());
            preStm.setBoolean(4, prop.isAvailable());
            preStm.setString(5, prop.getDescription());
            preStm.setString(6, prop.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteProp(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblProps Where PropID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateListProp(List<PropDTO> list) throws Exception {
        int count = 0;
        boolean check = false;
        try {
            String sql = "Update tblProps Set PropName = ?, Image = ?, "
                    + "Quantities = ?, Available = ?, Description = ? Where PropID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (PropDTO prop : list) {
                preStm.setString(1, prop.getName());
                preStm.setString(2, prop.getImage());
                preStm.setInt(3, prop.getQuantities());
                preStm.setBoolean(4, prop.isAvailable());
                preStm.setString(5, prop.getDescription());
                preStm.setString(6, prop.getId());
                preStm.executeUpdate();
                count++;
            }
            conn.commit();
            conn.setAutoCommit(true);
            check = (count == list.size());
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertPropScene(List<PropSceneDTO> list) throws Exception {
        boolean check = false;
        try {
            int count = 0;
            String sql = "Insert Into tblProps_Scene(PropID, SceneID, Quantities, DateFrom, DateTo) Values(?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (PropSceneDTO dto : list) {
                preStm.setString(1, dto.getProp().getId());
                preStm.setString(2, dto.getScene().getId());
                preStm.setInt(3, dto.getQuantities());
                preStm.setDate(4, java.sql.Date.valueOf(dto.getDateFrom()));
                preStm.setDate(5, java.sql.Date.valueOf(dto.getDateTo()));
                preStm.executeUpdate();
                count++;
            }
            conn.commit();
            conn.setAutoCommit(true);
            check = (count == list.size());
        } finally {
            closeConnection();
        }
        return check;
    }

    public String findActorByRole(String sceneID, String role) throws Exception {
        String actorID = null;
        try {
            String sql = "Select ActorID From tblActor_Scene Where SceneID = ? And Role = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, sceneID);
            preStm.setString(2, role);
            rs = preStm.executeQuery();
            if (rs.next()) {
                actorID = rs.getString("ActorID");
            }
        } finally {
            closeConnection();
        }
        return actorID;
    }

    public String findRoleByPrimaryKey(String sceneID, String actorID) throws Exception {
        String role = null;
        try {
            String sql = "Select Role From tblActor_Scene Where SceneID = ? And ActorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, sceneID);
            preStm.setString(2, actorID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public boolean insertActorScene(List<ActorSceneDTO> list) throws Exception {
        boolean check = false;
        try {
            int count = 0;
            String sql = "Insert Into tblActor_Scene(SceneID, ActorID, Role, Script) Values(?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            for (ActorSceneDTO dto : list) {
                count++;
                preStm.setString(1, dto.getScene().getId());
                preStm.setString(2, dto.getActor().getId());
                preStm.setString(3, dto.getRole());
                preStm.setString(4, dto.getScript());
                preStm.addBatch();
            }
            int[] records = preStm.executeBatch();
            check = (count == list.size());
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ActorDTO> findActorsByLikeName(String search) throws Exception {
        List<ActorDTO> list = null;
        try {
            String sql = "Select ActorID, Username, Fullname, Phone, Email, Image, Description From tblActors Where Fullname LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ActorDTO actor = new ActorDTO(rs.getString("ActorID"), rs.getString("Username"), rs.getString("Fullname"),
                        rs.getString("Phone"), rs.getString("Email"), rs.getString("Image"), rs.getString("Description"));
                list.add(actor);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ActorDTO> getAllActors() throws Exception {
        List<ActorDTO> list = null;
        try {
            String sql = "Select ActorID, Username, Fullname, Phone, Email, Image, Description From tblActors";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                ActorDTO actor = new ActorDTO(rs.getString("ActorID"), rs.getString("Username"), rs.getString("Fullname"),
                        rs.getString("Phone"), rs.getString("Email"), rs.getString("Image"), rs.getString("Description"));
                list.add(actor);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<DirectorDTO> findDirectorsByLikeName(String search) throws Exception {
        List<DirectorDTO> list = null;
        try {
            String sql = "Select DirectorID, Username, Fullname, "
                    + "Phone, Email From tblDirectors Where Fullname LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                DirectorDTO director = new DirectorDTO(rs.getString("DirectorID"), rs.getString("Username"),
                        rs.getString("Fullname"), rs.getString("Phone"), rs.getString("Email"));
                list.add(director);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<DirectorDTO> getAllDirectors() throws Exception {
        List<DirectorDTO> list = null;
        try {
            String sql = "Select DirectorID, Username, Fullname, "
                    + "Phone, Email From tblDirectors";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                DirectorDTO director = new DirectorDTO(rs.getString("DirectorID"), rs.getString("Username"),
                        rs.getString("Fullname"), rs.getString("Phone"), rs.getString("Email"));
                list.add(director);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<SceneDTO> findScenesByLikeName(String search) throws Exception {
        List<SceneDTO> list = null;
        try {
            String sql = "Select SceneID, SceneName, Location, DateFrom, DateTo, "
                    + "Description, TotalCuts, TotalProps, DirectorID From tblScenes Where SceneName Like ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                SceneDTO scene = new SceneDTO(rs.getString("SceneID"), rs.getString("SceneName"), rs.getString("Location"), rs.getString("DateFrom"),
                        rs.getString("DateTo"), rs.getString("Description"), rs.getInt("TotalCuts"), rs.getInt("TotalProps"), rs.getString("DirectorID"));
                list.add(scene);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<SceneDTO> getAllScenes() throws Exception {
        List<SceneDTO> list = null;
        try {
            String sql = "Select SceneID, SceneName, Location, DateFrom, DateTo, "
                    + "Description, TotalCuts, TotalProps, DirectorID From tblScenes";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                SceneDTO scene = new SceneDTO(rs.getString("SceneID"), rs.getString("SceneName"), rs.getString("Location"), rs.getString("DateFrom"),
                        rs.getString("DateTo"), rs.getString("Description"), rs.getInt("TotalCuts"), rs.getInt("TotalProps"), rs.getString("DirectorID"));
                list.add(scene);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<PropDTO> findPropsByLikeName(String search) throws Exception {
        List<PropDTO> list = null;
        PropDTO prop = null;
        try {
            String sql = "Select PropID, PropName, Image, Quantities, Available, Description From tblProps Where PropName LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                prop = new PropDTO(rs.getString("PropID"), rs.getString("PropName"), rs.getString("Image"),
                        rs.getString("Description"), rs.getInt("Quantities"), rs.getBoolean("Available"));
                list.add(prop);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<PropDTO> getAllProps() throws Exception {
        List<PropDTO> list = null;
        PropDTO prop = null;
        try {
            String sql = "Select PropID, PropName, Image, Quantities, Available, Description From tblProps";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                prop = new PropDTO(rs.getString("PropID"), rs.getString("PropName"), rs.getString("Image"),
                        rs.getString("Description"), rs.getInt("Quantities"), rs.getBoolean("Available"));
                list.add(prop);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<SceneDTO> getScenesOfDirector(String id) throws Exception {
        List<SceneDTO> list = null;
        try {
            String sql = "Select SceneID, SceneName, Location, DateFrom, DateTo, Description, TotalCuts, TotalProps,"
                    + "DirectorID From tblScenes S Where DirectorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                SceneDTO scene = new SceneDTO(rs.getString("SceneID"), rs.getString("SceneName"), rs.getString("Location"), rs.getString("DateFrom"),
                        rs.getString("DateTo"), rs.getString("Description"), rs.getInt("TotalCuts"), rs.getInt("TotalProps"), rs.getString("DirectorID"));
                list.add(scene);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<PropCartDTO> getAllPropCart(String directorID) throws Exception {
        List<PropCartDTO> list = null;
        PropCartDTO dto = null;
        try {
            String sql = "Select S.SceneID, S.SceneName, P.PropID, P.PropName, PS.Quantities, PS.DateFrom, PS.DateTo "
                    + "From tblScenes S, tblProps P, tblProps_Scene PS Where "
                    + "S.SceneID = PS.SceneID And PS.PropID = P.PropID AND S.DirectorID = ? Order By PS.DateFrom";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, directorID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                dto = new PropCartDTO(rs.getString("SceneID"), rs.getString("SceneName"),
                        rs.getString("PropID"), rs.getString("PropName"), rs.getString("DateFrom"), rs.getString("DateTo"), rs.getInt("Quantities"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ActorSceneDTO> getScheduleScene(String actorID) throws Exception {
        List<ActorSceneDTO> list = null;
        ActorSceneDTO dto = null;
        try {
            String sql = "Select S.SceneID, S.SceneName, S.DateTo, A.Role, A.Script From tblScenes S, tblActor_Scene A WHERE S.SceneID = A.SceneID AND A.ActorID = ? AND S.DateTo > ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            preStm.setDate(2, date);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                dto = new ActorSceneDTO(rs.getString("SceneID") + actorID, rs.getString("Role"));
                dto.setSceneName(rs.getString("SceneName"));
                dto.setScript(rs.getString("Script"));
                dto.setDate(rs.getString("DateTo"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ActorSceneDTO> getFilmedScene(String actorID) throws Exception {
        List<ActorSceneDTO> list = null;
        ActorSceneDTO dto = null;
        try {
            String sql = "Select S.SceneID, S.SceneName, S.DateTo, A.Role, A.Script From tblScenes S, tblActor_Scene A WHERE S.SceneID = A.SceneID AND A.ActorID = ? AND S.DateTo < ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            preStm.setDate(2, date);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                dto = new ActorSceneDTO(rs.getString("SceneID") + actorID, rs.getString("Role"));
                dto.setSceneName(rs.getString("SceneName"));
                dto.setScript(rs.getString("Script"));
                dto.setDate(rs.getString("DateTo"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteAllScene(String directorID) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblScenes Where DirectorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, directorID);
            check = preStm.executeUpdate() >= 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deletePropSceneBySceneID(String sceneID) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblProps_Scene Where SceneID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, sceneID);
            check = preStm.executeUpdate() >= 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deletePropSceneByPropID(String propID) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblProps_Scene Where PropID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, propID);
            check = preStm.executeUpdate() >= 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteActorSceneBySceneID(String sceneID) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblActor_Scene Where SceneID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, sceneID);
            check = preStm.executeUpdate() >= 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteActorSceneByActorID(String actorID) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete tblActor_Scene Where ActorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            check = preStm.executeUpdate() >= 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<String> checkScene(String directorID) throws Exception {
        List<String> list = null;
        try {
            String sql = "Select A.SceneID From tblScenes S, tblActor_Scene A Where S.DirectorID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, directorID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("SceneID"));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
