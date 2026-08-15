package com.sih.craftshieldb.passport_service.Repo;

import com.sih.craftshieldb.passport_service.Model.Crafts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CraftsRepo extends JpaRepository<Crafts,Long> {
}
