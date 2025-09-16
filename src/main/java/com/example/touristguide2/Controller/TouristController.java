package com.example.touristguide2.Controller;

import com.example.touristguide2.Model.TouristAttraction;
import com.example.touristguide2.Service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    @GetMapping("/{name}/tags")
    public String getTouristAttractionTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.findTouristAttractionByName(name);
        if (attraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", touristService.getTouristAttractionTags(name));

        return "tags";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction("", "", "",new ArrayList<>()));
        model.addAttribute("location", touristService.getAllLocations());
        model.addAttribute("Tags", touristService.getAllTags());
        return "redirect:/addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.saveAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.findTouristAttractionByName(name);
        if (attraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("Tags", touristService.getAllTags());
        model.addAttribute("Locations", touristService.getAllLocations());
        return "redirect:/editAttraction";
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateAttraction(String attractionName, String newDescription) {
        TouristAttraction updatedAttraction = touristService.updateAttraction(attractionName, newDescription);

        if (updatedAttraction != null) {
            return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return "redirect:/attractions";
    }

}
