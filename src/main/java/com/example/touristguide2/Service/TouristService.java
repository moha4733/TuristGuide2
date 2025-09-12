package com.example.touristguide2.Service;

import com.example.touristguide2.Model.TouristAttraction;
import com.example.touristguide2.Repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private TouristRepository repository;

    public TouristService(TouristRepository touristRepository){
        this.repository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    public TouristAttraction deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }

    public TouristAttraction findTouristAttractionByName(String name) {
        return repository.findTouristAttractionByName(name);
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        return repository.addTouristAttraction(touristAttraction);
    }

    public List<String> getTouristAttractionTags(String name) {
        return repository.getTouristAttractionTags();
    }

    public TouristAttraction saveAttraction(TouristAttraction attraction) {
        return repository.saveAttraction(attraction);
    }

//    public TouristAttraction updateAttraction(TouristAttraction attraction) {
//        TouristRepository.updateAttraction(attraction);
//        return attraction;
//    }

    public TouristAttraction updateAttraction(String name, String newDescription) {
        return repository.updateAttraction(name, newDescription);
    }

    public List<String> getAllTags() {
        return repository.getTouristAttractionTags();
    }

    public List<String> getAllLocations() {
        return repository.getLocation();
    }
}

