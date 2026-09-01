package com.sih.craftshieldb.passport_service.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artist_tips")
public class ArtistTip {

    @Id
    @Column(name = "artist_email")
    private  String artistEmail;

    @Column(name = "total_tips", nullable = false)
    private Double totalTips=0.0;

    public ArtistTip(){};

    public ArtistTip(String artistEmail, Double totalTips){
        this.artistEmail = artistEmail;
        this.totalTips = totalTips;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        this.artistEmail = artistEmail;
    }

    public Double getTotalTips() {
        return totalTips;
    }

    public void setTotalTips(Double totalTips) {
        this.totalTips = totalTips;
    }
}
