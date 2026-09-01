package com.sih.craftshieldb.passport_service.Controllers;

import com.sih.craftshieldb.passport_service.Model.TipRequest;
import com.sih.craftshieldb.passport_service.Service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tips")
@CrossOrigin
public class TipController {

    @Autowired
    private TipService tipService;

    @PostMapping("/add")
    public ResponseEntity<String> addTip(@RequestBody TipRequest request) {
        try {
            tipService.processTip(request.getCraftId(), request.getArtistEmail(), request.getTier());
            return ResponseEntity.ok("Tip processed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing tip: " + e.getMessage());
        }
    }
}