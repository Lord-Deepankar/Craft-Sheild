package com.sih.craftshieldb.passport_service.Controllers;

import com.sih.craftshieldb.passport_service.Model.qrScanResponse;
import com.sih.craftshieldb.passport_service.Service.ScanSecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/public")
public class PublicScanController {

    private final ScanSecurityService scanService;
    PublicScanController(ScanSecurityService scanService){
        this.scanService = scanService;
    }

    /*
    @GetMapping("/scan/{id}")
    public qrScanResponse getCraft(HttpServletRequest request , @PathVariable long id){
        String ip = request.getRemoteAddr();
        // IF THE STATUS IS COMPROMISED THEN IN FRONT SHE WILL HANDLE IT , CHECK THE FLAG AND INSTEAD OF craft details
        // SHE'LL DISPLAY A MESSAGE , saying , "Sorry Try again later"
        return scanService.getCraftDetails(ip,id);

    }*/

    @GetMapping("/scan/{id}")
    public qrScanResponse getCraft(HttpServletRequest request , @PathVariable long id){
        String ip = extractClientIp(request);
        // IF THE STATUS IS COMPROMISED THEN IN FRONT SHE WILL HANDLE IT , CHECK THE FLAG AND INSTEAD OF craft details
        // SHE'LL DISPLAY A MESSAGE , saying , "Sorry Try again later"
        return scanService.getCraftDetails(ip,id);

    }



    private String extractClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            // X-Forwarded-For can be a comma-separated chain of proxies;
            // the first entry is the original client.
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

}
