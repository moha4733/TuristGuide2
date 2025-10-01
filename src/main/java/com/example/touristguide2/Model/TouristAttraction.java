package com.example.touristguide2.Model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String location;
    private int tags;

    // Constructor
    public TouristAttraction(String name, String description, String location, int tags) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.tags = tags;
    }

    // Getters og setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getTags() {
        return tags;
    }
    public void setTags(int tags) {
        this.tags = tags;
    }
}
