package com.example.gebruiker.restaurant;

public class MenuItem {

    public String name;
    public String description;
    public String imageURL;
    private String price;
    public String category;

    public MenuItem(String name, String description, String imageURL, String price, String category) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.category = category;
    }

    // Getters and setters.
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getPrice() {
        String euro = "€" + price + "0";
        return euro;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
