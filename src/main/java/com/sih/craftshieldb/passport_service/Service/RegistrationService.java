package com.sih.craftshieldb.passport_service.Service;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import com.sih.craftshieldb.passport_service.Model.QrCodeEntity;
import com.sih.craftshieldb.passport_service.Repo.CraftsRepo;
import com.sih.craftshieldb.passport_service.Utility.IntegrityUtil;
import com.sih.craftshieldb.passport_service.Utility.QrGenerator;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final CraftsRepo craftsRepo;
    private final QrCodeEntity qrCodeEntity = new QrCodeEntity();
    public RegistrationService(CraftsRepo craftsRepo) {
        this.craftsRepo = craftsRepo;
    }


    public QrCodeEntity registerCraft(Crafts product) throws Exception {

        product.setStatus("AVAILABLE");
        Crafts lastCraft = craftsRepo.findTopByOrderByCraftIdDesc();

        String previousHash = (lastCraft != null)
                ? lastCraft.getDataHash()
                : "0000000000000000000000000000000000000000000000000000000000000000";


        String hash = IntegrityUtil.generateCraftHash(product , previousHash);
        product.setDataHash(hash);
        craftsRepo.save(product);


        // put the url which the qr code will redirect bascially the endpoint to our PublicScanController endpoint
        // which will then do security check and return accordingly
        // below url is temporary one until we actually host the frontend
        String url = "/api/scan/" + product.getCraftId();
        String qrCodeString = QrGenerator.generateQrCodeBase64(url);
        qrCodeEntity.setProductName(product.getProductName());
        qrCodeEntity.setQrCodeString(qrCodeString);
        return qrCodeEntity;
    }
}
