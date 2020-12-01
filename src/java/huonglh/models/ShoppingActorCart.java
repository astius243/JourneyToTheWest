/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.models;

import huonglh.dtos.ActorDTO;
import huonglh.dtos.ActorSceneDTO;
import huonglh.dtos.SceneDTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Hau Huong
 */
public class ShoppingActorCart implements Serializable {

    private HashMap<String, ActorSceneDTO> cart;

    public ShoppingActorCart() {
        cart = new HashMap<>();
    }

    public HashMap<String, ActorSceneDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, ActorSceneDTO> cart) {
        this.cart = cart;
    }

    public void addToCart(ActorSceneDTO dto) throws Exception {
        if (!this.cart.containsKey(dto.getId())) {
            this.cart.put(dto.getId(), dto);
        }
    }

    public void remove(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void updateCart(String id, String role) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setRole(id);
        }
    }
    
}
