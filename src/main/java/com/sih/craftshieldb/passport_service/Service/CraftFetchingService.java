package com.sih.craftshieldb.passport_service.Service;


import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraftFetchingService {

    private final CraftsRepo repo;

    public CraftFetchingService(CraftsRepo repo) {
        this.repo = repo;
    }


    public List<Crafts> getAllCrafts(String status) {
        return repo.findByStatus(status);
    }


    public List<Crafts> getCraftsNotSuspended(String suspended) {
        return repo.findByStatusNot(suspended);
    }
}
