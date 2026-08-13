package com.sih.craftshieldb.passport_service.Controllers;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArtisanController {


    @PostMapping
    public void registerProduct(@RequestBody Crafts product){}
}
