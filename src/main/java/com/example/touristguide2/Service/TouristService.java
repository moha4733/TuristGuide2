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

    public TouristAttraction deleteTouristAttraction(String name) {
        return repository.deleteTouristAttraction(name);
    }

    public TouristAttraction updateTouristAttraction(String name, String newDescription) {
        return repository.updateTouristAttraction(name, newDescription);
    }

    public TouristAttraction findTouristAttractionByName(String name) {
        return repository.findTouristAttractionByName(name);
    }

    public List<String> getTouristAttractionTags(String name) {
        return repository.getTouristAttractionTags();
    }

    public void saveAttraction(TouristAttraction attraction) {

    }

    public TouristAttraction updateTouristAttraction(TouristAttraction attraction) {
        return attraction;
    }
}

