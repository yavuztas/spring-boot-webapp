package dev.yavuztas.boilerplate.springbootwebapp.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * View model class for domain Item
 *
 * @author Yavuz Tas
 */
public class ItemView {

    private String name;
    private String game;
    private LocalDate expirationDate;
    private Long quantity;

    @JsonProperty("property")
    private final List<PropertyView> properties = new ArrayList<>();

    public ItemView() {
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public List<PropertyView> getProperties() {
        return properties;
    }
}
