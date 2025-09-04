package com.example.turistguide2.repository;

import com.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    public UserRepository() {
        touristAttractions.add(new TouristAttraction("Hej"));
        }

    public TouristAttraction addTouristAttraction(TouristAttraction attraction) {
        touristAttractions.add(attraction);
        return attraction;

    }
    public TouristAttraction updateTouristAttraction(String name, String description) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setDescription(description);
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction deleteTouristAttraction(String name) {
        TouristAttraction attractionToDelete = findTouristAttractionByName(name);
        if (attractionToDelete != null) {
            touristAttractions.remove(attractionToDelete);
            return attractionToDelete;
        }
        return null;
    }

    public List<TouristAttraction> getAllAttractions() {
        return new ArrayList<>(touristAttractions);
    }

    public TouristAttraction findTouristAttractionByName(String name) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }
}
