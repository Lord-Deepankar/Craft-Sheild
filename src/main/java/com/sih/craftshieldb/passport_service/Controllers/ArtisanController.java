package com.sih.craftshieldb.passport_service.Controllers;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Model.QrCodeEntity;
import com.sih.craftshieldb.passport_service.Service.RegistrationService;
import com.sih.craftshieldb.passport_service.Service.SoldOutService;
import com.sih.craftshieldb.passport_service.Service.UnSuspendingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArtisanController {


    private final UnSuspendingService unSuspendingService;
    private final SoldOutService soldOutService;
    private final RegistrationService registrationService;

    public ArtisanController(UnSuspendingService unSuspendingService, SoldOutService soldOutService, RegistrationService registrationService) {
        this.unSuspendingService = unSuspendingService;
        this.soldOutService = soldOutService;
        this.registrationService = registrationService;
    }


    @PostMapping("/register")
    public QrCodeEntity registerProduct(@RequestBody Crafts product) throws Exception {
        return registrationService.registerCraft(product);
    }


    // Map this endpoint to unsuspend button , by default the button will unsuspension
    @PutMapping("craft/{id}/unsuspend")
    public Crafts unSuspend(@PathVariable Long id){
        return unSuspendingService.unsuspendCraft(id);
    }

    @PutMapping("craft/{id}/sold")
    public Crafts markAsSold(@PathVariable Long id){
        return soldOutService.markAsSold(id);
    }
}
