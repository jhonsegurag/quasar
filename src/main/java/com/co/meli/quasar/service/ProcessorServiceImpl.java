package com.co.meli.quasar.service;

import com.co.meli.quasar.entity.Galaxy;
import com.co.meli.quasar.entity.Position;
import com.co.meli.quasar.entity.Space;
import com.co.meli.quasar.exception.LocationException;
import com.co.meli.quasar.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public class ProcessorServiceImpl implements IProcessorService {

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    MessageServiceImpl messageService;

    @Override
    public Space getSpace(RequestEntity<Galaxy> requestEntity) throws LocationException, MessageException {

        Galaxy galaxy = (Galaxy)requestEntity.getBody();
        locationService.initialize(galaxy);
        if (galaxy.getDistances().length < 3) {
            throw new LocationException("Distances insuficient");
        }
        if (galaxy.getPositions().length < 3) {
            throw new LocationException("Positions insuficient");
        }


        double[] location = locationService.getLocation(galaxy.getPositions(), galaxy.getDistances());
        Position spacePosition = new Position(location);

        if (galaxy.getMessages().size() < 3) {
            throw new MessageException("Messaages insuficient");
        }
        String message = "";


        return new Space(spacePosition, "");
    }
}
