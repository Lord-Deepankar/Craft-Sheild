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
    private QrCodeEntity qrCodeEntity = new QrCodeEntity();
    public RegistrationService(CraftsRepo craftsRepo) {
        this.craftsRepo = craftsRepo;
    }


    public QrCodeEntity registerCraft(Crafts product) throws Exception {

        String hash = IntegrityUtil.generateCraftHash(product);
        product.setDataHash(hash);
        product.setStatus("AVAILABLE");
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
