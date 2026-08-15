package com.sih.craftshieldb.passport_service.Utility;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Component
public class QrGenerator {
    public static String generateQrCodeBase64(String text) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Generate a 250x250 QR code matrix
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Write the matrix to the output stream as a PNG
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        // Convert the byte array to a Base64 string
        byte[] pngData = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(pngData);
    }
}
