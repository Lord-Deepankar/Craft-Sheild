package com.sih.craftshieldb.passport_service.Utility;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class IntegrityUtil {

    public static String generateCraftHash(Crafts craft, String previousHash) {
        String rawPayload;

        if (craft.getPayout() == null) {
            rawPayload = craft.getProductName() +
                    craft.getArtistName() +
                    craft.getFixedCost() +
                    craft.getMaterialDetails() +
                    previousHash;
        } else {
            rawPayload = craft.getProductName() +
                    craft.getArtistName() +
                    craft.getFixedCost() +
                    craft.getMaterialDetails() +
                    craft.getPayout() +
                    previousHash;
        }

        return sha256(rawPayload);
    }

    // Core SHA-256 execution method
    public static String sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error computing SHA-256 hash", e);
        }
    }

    // Simple verification check
    public static boolean verifyIntegrity(Crafts craftFromDb, String storedHash, String lastEntryHash) {
        String currentComputedHash = generateCraftHash(craftFromDb , lastEntryHash);
        return currentComputedHash.equals(storedHash);
    }
}
