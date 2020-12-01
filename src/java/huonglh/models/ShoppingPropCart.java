/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.models;

import huonglh.dtos.PropSceneDTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Hau Huong
 */
public class ShoppingPropCart implements Serializable {

    private HashMap<String, PropSceneDTO> cart;

    public ShoppingPropCart() {
        cart = new HashMap<>();
    }

    public HashMap<String, PropSceneDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, PropSceneDTO> cart) {
        this.cart = cart;
    }

    public void addToCart(PropSceneDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getId())) {
            int quantities = this.cart.get(dto.getId()).getQuantities() + 1;
            dto.setQuantities(quantities);
        }
        this.cart.put(dto.getId(), dto);
    }

    public void remove(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void updateCart(String id, int quantities) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantities(quantities);
        }
    }

    public int getQuantities(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            return this.cart.get(id).getQuantities();
        }
        return 0;
    }
}
