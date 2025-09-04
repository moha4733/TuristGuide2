package com.example.turistguide2.repository;

import com.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    // Liste med almindelige turistattraktioner (mock data)
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    // Konstruktør med eksempeldata (standardattraktion)
    public TouristAttractionRepository() {
        // Tilføjer en standardattraktion ved initialisering
        touristAttractions.add(new TouristAttraction(
                "Den Lille Havfrue",
                "En ikonisk statue på Langelinie.",
                "København",
                Arrays.asList("landmark", "statue", "historie")
        ));
    }

    // Tilføj en ny turistattraktion til listen
    public TouristAttraction addTouristAttraction(TouristAttraction attraction) {
        touristAttractions.add(attraction);
        return attraction;
    }

    // Opdater en eksisterende attraktion ved navn
    public TouristAttraction updateTouristAttraction(String name, String description) {
        for (TouristAttraction attraction : touristAttractions) {
            // Opdater beskrivelsen hvis navnet matcher
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setDescription(description);
                return attraction;
            }
        }
        return null; // Returner null hvis ikke fundet
    }

    // Slet en attraktion fra listen baseret på navn
    public TouristAttraction deleteTouristAttraction(String name) {
        // Find attraktionen først
        TouristAttraction attractionToDelete = findTouristAttractionByName(name);
        if (attractionToDelete != null) {
            touristAttractions.remove(attractionToDelete); // Fjern fra listen
            return attractionToDelete; // Returner den slettede
        }
        return null; // Returner null hvis ikke fundet
    }

    // Find en enkelt attraktion ved navn (case-insensitive)
    public TouristAttraction findTouristAttractionByName(String name) {
        // Gennemgå hele listen og retur 1. match
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null; // Returner null hvis ikke fundet
    }

    // Hent alle turistattraktioner (som en ny ArrayList for at undgå sideffects)
    public List<TouristAttraction> getAllAttractions() {
        return new ArrayList<>(touristAttractions);
    }
}
