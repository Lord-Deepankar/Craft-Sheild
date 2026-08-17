package com.sih.craftshieldb.passport_service.Repo;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CraftsRepo extends JpaRepository<Crafts,Long> {

    Crafts findTopByOrderByCraftIdDesc();
    Crafts findTopByCraftIdLessThanOrderByCraftIdDesc(Long currentCraftId);
    List<Crafts> findByStatus(String status);
    List<Crafts> findByStatusNot(String status);
    List<Crafts> findByArtistEmail(String artistEmail);
    List<Crafts> findByArtistEmailAndStatus(String artistEmail, String status);
}
