package com.co.meli.quasar.service;

import com.co.meli.quasar.entity.Galaxy;
import com.co.meli.quasar.entity.Space;
import com.co.meli.quasar.exception.LocationException;
import com.co.meli.quasar.exception.MessageException;
import org.springframework.http.RequestEntity;

public interface IProcessorService {

    public Space getSpace(RequestEntity<Galaxy> requestEntity) throws LocationException, MessageException;
}
