package com.co.meli.quasar.service;

import com.co.meli.quasar.dto.TopSecretSplitRequest;
import com.co.meli.quasar.entity.Galaxy;
import com.co.meli.quasar.entity.Satelite;
import com.co.meli.quasar.entity.Space;
import com.co.meli.quasar.exception.LocationException;
import com.co.meli.quasar.exception.MessageException;
import com.co.meli.quasar.exception.QuasarException;
import com.co.meli.quasar.exception.SateliteException;
import org.springframework.http.RequestEntity;

public interface IProcessorService {

    public Space getSpace(RequestEntity<Galaxy> requestEntity) throws LocationException, MessageException;

    public Satelite addSatelite(String name, RequestEntity<TopSecretSplitRequest> requestEntity) throws SateliteException;

    public Space getSpace() throws QuasarException, LocationException, MessageException;
}
