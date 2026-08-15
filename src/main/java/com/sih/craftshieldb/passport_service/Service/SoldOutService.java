package com.sih.craftshieldb.passport_service.Service;


import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import org.springframework.stereotype.Service;

// it's for the artist to mark the craft as soldout from the ui itself
@Service
public class SoldOutService {

    private final CraftsRepo cRepo;
    SoldOutService(CraftsRepo cRepo){
        this.cRepo = cRepo;
    }

    public Crafts markAsSold(Long id) {
        Crafts craft = cRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid Request: Craft not found with ID: " + id));
        craft.setStatus("SOLD_OUT");
        cRepo.save(craft);
        return craft;
    }
}
