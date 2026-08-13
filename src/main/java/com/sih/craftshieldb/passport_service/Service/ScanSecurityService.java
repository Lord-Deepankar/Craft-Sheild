package com.sih.craftshieldb.passport_service.Service;

import com.sih.craftshieldb.passport_service.Model.qrScanResponse;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import org.springframework.stereotype.Service;

@Service
public class ScanSecurityService {


    private final CraftsRepo craftsRepo;
    ScanSecurityService(CraftsRepo craftsRepo){
        this.craftsRepo = craftsRepo;
    }

    public qrScanResponse getCraftDetails(String ip, long id) {
    }
}
