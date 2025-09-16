package com.example.touristguide2.Repository;

import com.example.touristguide2.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Den Lille Havfrue", "En ikonisk statue på Langelinie.", "København", Arrays.asList("Landmark", "Statue", "Historie")),
            new TouristAttraction("Junes El-Sayed", "Useriøs spiller.", "Nykøbing Falster", Arrays.asList("Fodbold", "Spiller", "Professionel diver")),
            new TouristAttraction("Bella Sky", "En af Skandinaviens flotteste hoteller.", "Amager", Arrays.asList("Hotel", "Bygning", "Arkitektur")),
            new TouristAttraction("Amalienborg", "Det danske kongehus' residens.", "København", Arrays.asList("Slot", "Kongeligt", "Historie")),
            new TouristAttraction("SMK", "Museum for kunst.", "København", Arrays.asList("Kunst", "Galleri", "Museum"))
    ));

    public List<TouristAttraction> getAllAttractions() {
        return attractions;
    }

    public TouristAttraction addTouristAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(String name, String description) {
        for (TouristAttraction attraction : attractions) {
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
            attractions.remove(attractionToDelete);
            return attractionToDelete;
        }
        return null;
    }

    public TouristAttraction findTouristAttractionByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    public List<String> getTouristAttractionTags(String name) {
        return attractions.stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(TouristAttraction::getTags)
                .orElse(Collections.emptyList());
    }

    public List<String> getAllTags() {
        return attractions.stream()
                .flatMap(a -> a.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }



    public List<String> getAllLocation() {
        return attractions.stream()
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
            attractions.add(attraction);
            return attraction;
        }
    }

}
