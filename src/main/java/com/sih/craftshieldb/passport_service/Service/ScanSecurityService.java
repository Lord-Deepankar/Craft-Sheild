package com.sih.craftshieldb.passport_service.Service;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Model.qrScanResponse;
import com.sih.craftshieldb.passport_service.Model.scanTelemetry;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import com.sih.craftshieldb.passport_service.Repo.scanTelemetryRepo;
import com.sih.craftshieldb.passport_service.Utility.IntegrityUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScanSecurityService {


    private final CraftsRepo craftsRepo;
    private final scanTelemetryRepo telemetryRepo;
    ScanSecurityService(CraftsRepo craftsRepo,  scanTelemetryRepo telemetryRepo){
        this.craftsRepo = craftsRepo;
        this.telemetryRepo = telemetryRepo;
    }


    public qrScanResponse getCraftDetails(String ip, long id) {

        Crafts craft = craftsRepo.findById(id).orElse(null);
        qrScanResponse response = new qrScanResponse();
        if (craft == null) {
            response.setStatus("INVALID QR");
            return response;
        }
        scanTelemetry telemetryObj = new scanTelemetry();
        telemetryObj.setCraftId(id);
        telemetryObj.setIpAddress(ip);
        telemetryObj.setScanTime(LocalDateTime.now());
        telemetryRepo.save(telemetryObj);


        Crafts previousCraft = craftsRepo.findTopByCraftIdLessThanOrderByCraftIdDesc(craft.getCraftId());

        String previousHash = (previousCraft != null)
                ? previousCraft.getDataHash()
                : "0000000000000000000000000000000000000000000000000000000000000000";


        int uniqueIps = velocityScan(craft.getCraftId());
        if (uniqueIps < 50) {
            if ("COMPROMISED".equals(craft.getStatus())) {
                response.setStatus("COMPROMISED");
                return response;
            }

            if ("SOLD_OUT".equals(craft.getStatus())) {
                response.setStatus("SOLD_OUT");
                return response;
            }

            if ("AVAILABLE".equals(craft.getStatus())) {

                if (IntegrityUtil.verifyIntegrity(craft, craft.getDataHash(), previousHash)) {
                    // the craft data is intact
                    response.setArtistName(craft.getArtistName());
                    response.setHistory(craft.getHistory());
                    response.setMaterialDetails(craft.getMaterialDetails());
                    response.setPrice(craft.getFixedCost());
                    response.setProductName(craft.getProductName());
                    response.setPayout(craft.getPayout());
                    response.setArtistEmail(craft.getArtistEmail());
                    response.setStatus("SECURE");
                } else {
                    // send no data back to frontend in case of compromise or sold out
                    response.setStatus("COMPROMISED");
                    craft.setStatus("COMPROMISED");
                    craftsRepo.save(craft);
                }
                return response;
            }
            else {
                response.setStatus("SOLD_OUT");
                return response;
            }
        }
        else{
            response.setStatus("SUSPENDED");
            craft.setStatus("SUSPENDED");
            craftsRepo.save(craft);
            return response;
        }
    }
    int velocityScan(Long id){
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        return telemetryRepo.countDistinctIpByCraftIdAndScanTimeAfter(id, oneHourAgo);
    }

}
