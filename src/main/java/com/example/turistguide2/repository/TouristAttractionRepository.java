package com.example.turistguide2.repository;

import com.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    // Constructor with sample data
    public TouristAttractionRepository() {
        // Add a default attraction when the repository is initialized
        touristAttractions.add(new TouristAttraction(
                "Den Lille Havfrue",
                "En ikonisk statue på Langelinie.",
                "København",
                Arrays.asList("landmark", "statue", "historie")
        ));
    }

    // Add a new attraction
    public TouristAttraction addTouristAttraction(TouristAttraction attraction) {
        touristAttractions.add(attraction);
        return attraction;
    }

    // Update an attraction by name
    public TouristAttraction updateTouristAttraction(String name, String description) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setDescription(description);
                return attraction;
            }
        }
        return null;
    }

    // Delete an attraction by name
    public TouristAttraction deleteTouristAttraction(String name) {
        TouristAttraction attractionToDelete = findTouristAttractionByName(name);
        if (attractionToDelete != null) {
            touristAttractions.remove(attractionToDelete);
            return attractionToDelete;
        }
        return null;
    }

    // Find an attraction by name
    public TouristAttraction findTouristAttractionByName(String name) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    // Get all attractions
    public List<TouristAttraction> getAllAttractions() {
        return new ArrayList<>(touristAttractions);
    }
}
