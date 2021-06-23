package com.co.meli.quasar.controller;

import com.co.meli.quasar.dto.TopSecretSplitRequest;
import com.co.meli.quasar.entity.Galaxy;
import com.co.meli.quasar.entity.Satelite;
import com.co.meli.quasar.exception.LocationException;
import com.co.meli.quasar.exception.MessageException;
import com.co.meli.quasar.exception.QuasarException;
import com.co.meli.quasar.exception.SateliteException;
import com.co.meli.quasar.service.ProcessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class QuasarController {

    @Autowired
    ProcessorServiceImpl processorServiceImpl;

    @GetMapping(value = "/greet")
    public String greet(){
        return "Hello Quasar";
    }

    @PostMapping(value = "/topsecret", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topsecret(RequestEntity<Galaxy> requestEntity) throws MessageException, LocationException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(processorServiceImpl.getSpace(requestEntity));
        } catch (MessageException messageException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageException.getMessage(), messageException);
        } catch (LocationException locationException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, locationException.getMessage(), locationException);
        }

    }

    @PostMapping(value = "/topsecretsplit/{satellite_name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topsecretsplit(@PathVariable(value = "satellite_name") String name, RequestEntity<TopSecretSplitRequest> requestEntity) throws SateliteException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(processorServiceImpl.addSatelite(name, requestEntity));
        } catch (SateliteException sateliteException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, sateliteException.getMessage(), sateliteException);
        }
    }

    @GetMapping(value = "/topsecretsplit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topsecretsplit() throws QuasarException, MessageException, LocationException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(processorServiceImpl.getSpace());
        } catch (MessageException messageException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageException.getMessage(), messageException);
        } catch (LocationException locationException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, locationException.getMessage(), locationException);
        }
    }

}
