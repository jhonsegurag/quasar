package com.co.meli.quasar.dto;

import com.co.meli.quasar.entity.Satelite;

import java.util.List;

public class TopSecretRequest {
    private List<Satelite> satellites;

    public TopSecretRequest(List<Satelite> satellites) {
        this.satellites = satellites;
    }

    public List<Satelite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satelite> satellites) {
        this.satellites = satellites;
    }
}
