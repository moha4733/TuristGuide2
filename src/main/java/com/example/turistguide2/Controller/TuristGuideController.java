package com.example.turistguide2.Controller;

import com.example.turistguide2.Model.TouristAttraction;
import com.example.turistguide2.Service.TuristGuideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/attactions")
public class TuristGuideController {

    private final TuristGuideService turistGuideService;

    public TuristGuideController (TuristGuideService turistGuideService) {
        this.turistGuideService = turistGuideService;
    }

    @GetMapping()
    public ResponseEntity<List<TouristAttraction>> getTouristAttractions() {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public class T
}


