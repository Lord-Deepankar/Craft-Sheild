package com.sih.craftshieldb.passport_service.Model;

public class TipRequest {

    private  long craftId;
    private String artistEmail;
    private int tier;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        this.artistEmail = artistEmail;
    }

    public long getCraftId() {
        return craftId;
    }

    public void setCraftId(long craftId) {
        this.craftId = craftId;
    }




}
