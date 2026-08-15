package com.sih.craftshieldb.passport_service.Service;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import org.springframework.stereotype.Service;

//it's there for artistian to unsuspend their craft fromt he suspended table , if they think it was false alarm
@Service
public class UnSuspendingService {



    private final CraftsRepo cRepo;
    UnSuspendingService(CraftsRepo cRepo){
        this.cRepo = cRepo;
    }

    public Crafts unsuspendCraft(Long id) {
        Crafts craft = cRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid Request: Craft not found with ID: " + id));
        craft.setStatus("AVAILABLE");
        cRepo.save(craft);
        return craft;
    }
}
