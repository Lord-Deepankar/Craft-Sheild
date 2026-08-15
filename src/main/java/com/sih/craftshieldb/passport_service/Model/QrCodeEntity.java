package com.sih.craftshieldb.passport_service.Model;




public class QrCodeEntity {

    private String productName;
    private String qrCodeString;

    public String getProductName() {
        return productName;
    }

    public String getQrCodeString() {
        return qrCodeString;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setQrCodeString(String qrCodeString) {
        this.qrCodeString = qrCodeString;
    }

}
