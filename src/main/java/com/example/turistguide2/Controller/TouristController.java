package com.example.turistguide2.Controller;

import ch.qos.logback.core.model.Model;
import com.example.turistguide2.Model.TouristAttraction;
import com.example.turistguide2.Service.TuristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TuristService turistService;

    public TouristController(TuristService turistService) {
        this.turistService = turistService;
    }

    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getTouristAttractions() {
        List<TouristAttraction> attractions = turistService.getAllAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getTouristAttraction(@PathVariable String name) {
        TouristAttraction attraction = turistService.findTouristAttractionByName(name);
        if (attraction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

    @GetMapping("/{name}/tags")
    public ResponseEntity<List<String>> getTouristAttractionTags(@PathVariable String name) {
        List<String> tags = turistService.getTouristAttractionTags(name);
        if (tags == null || tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        turistService.saveAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        // ...
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateTouristAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction updatedAttraction = turistService.updateTouristAttraction(attraction);
        if (updatedAttraction != null) {
            return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteTouristAttraction(@PathVariable String name) {
        TouristAttraction deletedAttraction = turistService.deleteTouristAttraction(name);
        if (deletedAttraction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedAttraction, HttpStatus.OK);
    }
}