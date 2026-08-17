package com.sih.craftshieldb.passport_service.Service;


import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final CraftsRepo craftsRepo ;
    SearchService(CraftsRepo craftsRepo)
    {
        this.craftsRepo = craftsRepo;
    }

    public List<Crafts> findByOwnerEmail(String email) {
        return craftsRepo.findByArtistEmail(email);
    }

    public List<Crafts> getSuspendedCraftsByEmail(String email) {
        return craftsRepo.findByArtistEmailAndStatus(email,"SUSPENDED");
    }
}
