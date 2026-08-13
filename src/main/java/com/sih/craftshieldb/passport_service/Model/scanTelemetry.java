package com.sih.craftshieldb.passport_service.Model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "scan_telemetry")
public class scanTelemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long craftId;
    private String ipAddress;
    private LocalDateTime scanTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCraftId() {
        return craftId;
    }

    public void setCraftId(long craftId) {
        this.craftId = craftId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }


    public void ScanTelemetry() {
    }
}
