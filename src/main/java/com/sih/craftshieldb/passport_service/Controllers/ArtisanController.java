package com.sih.craftshieldb.passport_service.Controllers;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Model.QrCodeEntity;
import com.sih.craftshieldb.passport_service.Service.CraftFetchingService;
import com.sih.craftshieldb.passport_service.Service.RegistrationService;
import com.sih.craftshieldb.passport_service.Service.SoldOutService;
import com.sih.craftshieldb.passport_service.Service.UnSuspendingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/artist")
public class ArtisanController {


    private final UnSuspendingService unSuspendingService;
    private final SoldOutService soldOutService;
    private final RegistrationService registrationService;
    private final CraftFetchingService craftFetchingService;

    public ArtisanController(UnSuspendingService unSuspendingService, SoldOutService soldOutService, RegistrationService registrationService, CraftFetchingService craftFetchingService) {
        this.unSuspendingService = unSuspendingService;
        this.soldOutService = soldOutService;
        this.registrationService = registrationService;
        this.craftFetchingService = craftFetchingService;
    }

    // suspended list table in frontend
    @GetMapping("/crafts/suspended")
    public List<Crafts> getSuspendedCrafts(){
        return craftFetchingService.getAllCrafts("SUSPENDED");
    }


    // all crafts beside suspended in the frontend ui
    @GetMapping("/crafts/general")
    public List<Crafts> getAllCraftsBesideSuspended(){
        return craftFetchingService.getCraftsNotSuspended("SUSPENDED");
    }

    @PostMapping("/register")
    public QrCodeEntity registerProduct(@RequestBody Crafts product) throws Exception {
        return registrationService.registerCraft(product);
    }


    // Map this endpoint to unsuspend button , by default the button will unsuspension
    @PutMapping("/craft/{id}/unsuspend")
    public Crafts unSuspend(@PathVariable Long id){
        return unSuspendingService.unsuspendCraft(id);
    }

    // Map this endpoint to sold out button for each product
    @PutMapping("/craft/{id}/sold")
    public Crafts markAsSold(@PathVariable Long id){
        return soldOutService.markAsSold(id);
    }
}
