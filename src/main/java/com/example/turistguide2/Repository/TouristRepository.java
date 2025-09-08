package com.example.turistguide2.Repository;

import com.example.turistguide2.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TouristRepository {
    // Liste med almindelige turistattraktioner (mock data)
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    // Konstruktør med eksempeldata (standardattraktion)
    public TouristRepository() {
        // Tilføjer en standardattraktion ved initialisering
        touristAttractions.add(new TouristAttraction(
                "Den Lille Havfrue",
                "En ikonisk statue på Langelinie.",
                "København",
                Arrays.asList("landmark", "statue", "historie")
        ));
        touristAttractions.add(new TouristAttraction(
                "Junes El-Sayed",
                "useriøs spiller.",
                "Nykøbing Falster",
                Arrays.asList("fodbold", "spiller", "professionel diver")
        ));
        touristAttractions.add(new TouristAttraction(
                "Bella Sky",
                "En af Skandinaviens flotteste hoteller.",   // rettet tekst
                "Amager",
                Arrays.asList("hotel", "bygning", "arkitektur")
        ));
        touristAttractions.add(new TouristAttraction(
                "Amalienborg",
                "Det danske kongehus' residens.",            // rettet tekst
                "København",
                Arrays.asList("slot", "kongeligt", "historie")
        ));
        touristAttractions.add(new TouristAttraction(
                "SMK",
                "Museum for kunst.",
                "København",
                Arrays.asList("kunst", "galleri", "museum")
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
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null; // Returner null hvis ikke fundet
    }

    // Hent alle turistattraktioner (som en ny ArrayList for at undgå sideeffects)
    public List<TouristAttraction> getAllAttractions() {
        return new ArrayList<>(touristAttractions);
    }

    // Hent alle unikke tags fra attraktionerne
    public List<String> getTouristAttractionTags() {
        return touristAttractions.stream()
                .flatMap(attraction -> attraction.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // Gem/overskriv en attraktion (tilføj hvis den ikke findes, ellers opdater)
    public TouristAttraction saveAttraction(TouristAttraction attraction) {
        TouristAttraction existing = findTouristAttractionByName(attraction.getName());
        if (existing != null) {
            // Hvis attraktionen findes → opdater dens felter
            existing.setDescription(attraction.getDescription());
            existing.setLocation(attraction.getLocation());
            existing.setTags(attraction.getTags());
            return existing;
        } else {
            // Hvis den ikke findes → tilføj ny
            touristAttractions.add(attraction);
            return attraction;
        }
    }
}
