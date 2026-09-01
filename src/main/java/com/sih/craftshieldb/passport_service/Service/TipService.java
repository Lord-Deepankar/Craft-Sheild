package com.sih.craftshieldb.passport_service.Service;

import com.sih.craftshieldb.passport_service.Model.ArtistTip;
import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Repo.ArtistTipRepo;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TipService {

    @Autowired
    private CraftsRepo craftsRepo;

    @Autowired
    private ArtistTipRepo tipRepo;

    @Transactional
    public void processTip(long craftId, String artistEmail, int percentageTier) {
        Crafts craft = craftsRepo.findById(craftId)
                .orElseThrow(() -> new RuntimeException("Craft not found with ID: " + craftId));

        double price = craft.getFixedCost() != null ? craft.getFixedCost() : 0.0;


        double tipAmount = price * (percentageTier / 100.0);

        Optional<ArtistTip> existingTip = tipRepo.findById(artistEmail);
        if (existingTip.isEmpty()) {
            tipRepo.save(new ArtistTip(artistEmail, 0.0));
        }
        tipRepo.addTipAtomically(artistEmail, tipAmount);
    }
}