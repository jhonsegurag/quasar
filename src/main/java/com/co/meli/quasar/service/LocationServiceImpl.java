package com.co.meli.quasar.service;

import com.co.meli.quasar.entity.Galaxy;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LocationServiceImpl implements ILocationService{

    @Autowired
    private Environment environment;

    @Override
    public void initialize(Galaxy galaxy) {
        double[][] positions = new double[galaxy.getSatellites().size()][];
        String[] location;
        double[] positionItem = new double[2];
        for (int i = 0; i < galaxy.getSatellites().size(); i++) {
            location = environment.getProperty("quasar.sattelite."+ i +".position").split(",");
//            positionItem[0] = Double.valueOf(location[0]);
//            positionItem[1] = Double.valueOf(location[1]);
//            positions[i] = positionItem;
//            positionItem = new double[2];

            positions[i] = Arrays.stream(location)
                    .map(Double::valueOf)
                    .mapToDouble(Double::doubleValue)
                    .toArray();
        }

        galaxy.setPositions(positions);
    }

    @Override
    public double[] getLocation(double[][] positions, double[] distances) {
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        return optimum.getPoint().toArray();
    }
}
