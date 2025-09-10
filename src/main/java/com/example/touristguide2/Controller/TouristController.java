package com.example.touristguide2.Controller;

import com.example.touristguide2.Model.TouristAttraction;
import com.example.touristguide2.Service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    // Viser listen af attraktioner i attractionList.html
    @GetMapping
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList"; // -> attractionList.html
    }

    // Viser tags for en specifik attraktion i tags.html
    @GetMapping("/{name}/tags")
    public String getTouristAttractionTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.findTouristAttractionByName(name);
        if (attraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", touristService.getTouristAttractionTags(name));
        return "tags"; // -> tags.html
    }

    // Viser form til at tilføje en ny attraktion
    @GetMapping("/add")
    public String showAddForm() {
        return "addAttraction"; // -> addAttraction.html
    }

    // Gemmer ny attraktion
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.saveAttraction(attraction);
        return "redirect:/attractions";
    }
}
