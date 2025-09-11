package com.example.touristguide2.Repository;

import com.example.touristguide2.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        touristAttractions.add(new TouristAttraction("Den Lille Havfrue", "En ikonisk statue på Langelinie.", "København", Arrays.asList("landmark", "statue", "historie")));
        touristAttractions.add(new TouristAttraction("Junes El-Sayed", "useriøs spiller.", "Nykøbing Falster", Arrays.asList("fodbold", "spiller", "professionel diver")));
        touristAttractions.add(new TouristAttraction("Bella Sky", "En af Skandinaviens flotteste hoteller.", "Amager", Arrays.asList("hotel", "bygning", "arkitektur")));
        touristAttractions.add(new TouristAttraction("Amalienborg", "Det danske kongehus' residens.", "København", Arrays.asList("slot", "kongeligt", "historie")));
        touristAttractions.add(new TouristAttraction("SMK", "Museum for kunst.", "København", Arrays.asList("kunst", "galleri", "museum")));
    }

    public TouristAttraction addTouristAttraction(TouristAttraction attraction) {
        touristAttractions.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(String name, String description) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setDescription(description);
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction deleteAttraction(String name) {
        TouristAttraction attractionToDelete = findTouristAttractionByName(name);
        if (attractionToDelete != null) {
            touristAttractions.remove(attractionToDelete);
            return attractionToDelete;
        }
        return null;
    }

    public TouristAttraction findTouristAttractionByName(String name) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    public List<TouristAttraction> getAllAttractions() {
        return new ArrayList<>(touristAttractions);
    }

    public List<String> getTouristAttractionTags() {
        return touristAttractions.stream()
                .flatMap(attraction -> attraction.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getLocation() {
        return touristAttractions.stream()
                .map(TouristAttraction::getLocation)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public TouristAttraction saveAttraction(TouristAttraction attraction) {
        TouristAttraction existing = findTouristAttractionByName(attraction.getName());
        if (existing != null) {
            existing.setDescription(attraction.getDescription());
            existing.setLocation(attraction.getLocation());
            existing.setTags(attraction.getTags());
            return existing;
        } else {
            touristAttractions.add(attraction);
            return attraction;
        }
    }
}
