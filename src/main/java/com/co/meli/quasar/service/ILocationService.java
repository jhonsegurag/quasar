package com.co.meli.quasar.service;

import com.co.meli.quasar.entity.Galaxy;

public interface ILocationService {
    void initialize (Galaxy galaxy);

    double[] getLocation (double[][] positions, double[] distances);
}
