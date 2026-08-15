package com.sih.craftshieldb.passport_service.Model;


import org.springframework.stereotype.Component;


public class QrCodeEntity {

    private String productName;
    private String qrCodeString;

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setQrCodeString(String qrCodeString) {
        this.qrCodeString = qrCodeString;
    }

}
