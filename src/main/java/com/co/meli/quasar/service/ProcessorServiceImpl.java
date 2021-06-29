package com.co.meli.quasar.service;

import com.co.meli.quasar.dto.TopSecretRequest;
import com.co.meli.quasar.dto.TopSecretSplitRequest;
import com.co.meli.quasar.entity.Galaxy;
import com.co.meli.quasar.entity.Position;
import com.co.meli.quasar.entity.Satelite;
import com.co.meli.quasar.entity.Space;
import com.co.meli.quasar.exception.LocationException;
import com.co.meli.quasar.exception.MessageException;
import com.co.meli.quasar.exception.QuasarException;
import com.co.meli.quasar.exception.SateliteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessorServiceImpl implements IProcessorService {

    private final List<Satelite> cache = new ArrayList<>();

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    MessageServiceImpl messageService;

    public Space getSpace(RequestEntity<TopSecretRequest> requestEntity) throws LocationException, MessageException {

        TopSecretRequest topSecretRequest = (TopSecretRequest)requestEntity.getBody();
        Galaxy galaxy = new Galaxy();
        galaxy.setSatellites(topSecretRequest.getSatellites());
        locationService.initialize(galaxy);
        if (galaxy.getDistances().length < 3) {
            throw new LocationException("Insuficient Distances");
        }
        if (galaxy.getPositions().length < 3) {
            throw new LocationException("Insuficient Positions");
        }

        double[] location = locationService.getLocation(galaxy.getPositions(), galaxy.getDistances());
        Position spacePosition = new Position(location);

        if (galaxy.getMessages().size() < 3) {
            throw new MessageException("Insuficient Messages");
        }
        String message = messageService.getMessage(galaxy.getMessages());


        return new Space(spacePosition , message);
    }

    @Override
    public Satelite addSatelite(String name, RequestEntity<TopSecretSplitRequest> requestEntity) throws SateliteException {

        TopSecretSplitRequest topSecretSplitRequest = (TopSecretSplitRequest)requestEntity.getBody();
        Satelite satelite = new Satelite(name, topSecretSplitRequest.getDistance(), topSecretSplitRequest.getMessage());
        cache.add(satelite);

        return satelite;
    }

    @Override
    public Space getSpace() throws QuasarException, LocationException, MessageException {
        if(cache.size() != 3) {
            throw new QuasarException("Insuficient Satelites");
        }
        Galaxy galaxy = new Galaxy();
        galaxy.setSatellites(cache);
        locationService.initialize(galaxy);
        if (galaxy.getDistances().length < 3) {
            throw new LocationException("Insuficient Distances");
        }
        if (galaxy.getPositions().length < 3) {
            throw new LocationException("Insuficient Positions");
        }

        double[] location = locationService.getLocation(galaxy.getPositions(), galaxy.getDistances());
        Position spacePosition = new Position(location);

        if (galaxy.getMessages().size() < 3) {
            throw new MessageException("Insuficient Messages");
        }
        String message = messageService.getMessage(galaxy.getMessages());


        return new Space(spacePosition , message);
    }

}
