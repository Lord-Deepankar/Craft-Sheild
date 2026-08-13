package com.sih.craftshieldb.passport_service.Repo;

import com.sih.craftshieldb.passport_service.Model.scanTelemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface scanTelemetryRepo extends JpaRepository<scanTelemetry, Integer> {

    @Query("SELECT COUNT(DISTINCT s.ipAddress) FROM scanTelemetry  s WHERE s.craftId = :craftId AND s.scanTime >= :oneHourAgo")
    int countDistinctIpByCraftIdAndScanTimeAfter(Long craftId, LocalDateTime oneHourAgo);
}
