package com.sih.craftshieldb.passport_service.Model;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Crafts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long craftId;


    private String artistName;
    private String productName;

    @Column(length = 1000) // Making this larger for detailed descriptions
    private String materialDetails;

    @Column(length = 2000) // Making this larger for long histories
    private String history;

    private Double fixedCost;
    private Double payout; // Will be null if solo artist

    private String status; // e.g., "AVAILABLE", "SOLD", "SUSPENDED"
    private String dataHash;

    public void Craft() {
    }
    public String getDataHash() {
        return dataHash;
    }

    public void setDataHash(String dataHash) {
        this.dataHash = dataHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    public Double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(Double fixedCost) {
        this.fixedCost = fixedCost;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getMaterialDetails() {
        return materialDetails;
    }

    public void setMaterialDetails(String materialDetails) {
        this.materialDetails = materialDetails;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getCraftId() {
        return craftId;
    }

    public void setCraftId(int craftId) {
        this.craftId = craftId;
    }
}
