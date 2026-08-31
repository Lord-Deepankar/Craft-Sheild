package com.sih.craftshieldb.passport_service.Model;



public class qrScanResponse {

    private String status;
    private String productName;
    private double price;
    private String materialDetails;
    private String history;
    private String artistName;

    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    private Double payout;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMaterialDetails() {
        return materialDetails;
    }

    public void setMaterialDetails(String materialDetails) {
        this.materialDetails = materialDetails;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
