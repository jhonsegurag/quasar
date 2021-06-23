package com.co.meli.quasar.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Galaxy {
    private List<Satelite> satellites;

    public List<Satelite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satelite> satellites) {
        this.satellites = satellites;
    }

    public double[] getDistances(){

        double[] distances = new double[satellites.size()];
        for (int i = 0; i < satellites.size(); i ++) {
            distances[i] = satellites.get(i).getDistance();
        }

        return distances;
    }

    public double[][] getPositions(){
        double[][] positions =  new double[satellites.size()][];
        double[] positionItem;
        String[] points;
        for (int i = 0; i < satellites.size(); i++) {
            positionItem = new double[2];
            positionItem[0] = satellites.get(i).getPosition().getX();
            positionItem[1] = satellites.get(i).getPosition().getY();
            positions[i] = positionItem;
        }
        return positions;
    }

    public void setPositions(double[][] positions){
        Position positionItem = null;
        for (int i = 0; i < positions.length; i++) {
            positionItem = new Position(positions[i]);
            satellites.get(i).setPosition(positionItem);
        }
    }

    public List<List<String>> getMessages(){
        List<List<String>> messages = new ArrayList<>();
        for (int i = 0; i < satellites.size() ; i++) {
            messages.add(satellites.get(i).getMessage());
        }
        return messages;
    }
}
