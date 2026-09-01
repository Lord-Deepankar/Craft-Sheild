package com.sih.craftshieldb.passport_service.Repo;

import com.sih.craftshieldb.passport_service.Model.ArtistTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistTipRepo extends JpaRepository<ArtistTip , String> {

    // Implementing atomic increment to prevent race condition, like two user giving tip at the same time
    @Modifying
    @Query("UPDATE ArtistTip t SET t.totalTips = t.totalTips + :tipAmount WHERE t.artistEmail = :email")
    void addTipAtomically(@Param("email") String email, @Param("tipAmount") Double tipAmount);
}
