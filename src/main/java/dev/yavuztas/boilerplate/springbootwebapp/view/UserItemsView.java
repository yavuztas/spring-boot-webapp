package dev.yavuztas.boilerplate.springbootwebapp.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * View model class for userItems web request.
 *
 * @author Yavuz Tas
 */
public class UserItemsView {

    private String username;

    @JsonProperty("item")
    private List<ItemView> items = new ArrayList<>();

    public UserItemsView() {
    }

    public String getUsername() {
        return username;
    }

    public List<ItemView> getItems() {
        return items;
    }
}
