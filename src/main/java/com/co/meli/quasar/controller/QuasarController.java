package com.co.meli.quasar.controller;

import com.co.meli.quasar.dto.TopSecretRequest;
import com.co.meli.quasar.dto.TopSecretResponse;
import com.co.meli.quasar.dto.TopSecretSplitRequest;
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
    public ResponseEntity topsecret(RequestEntity<TopSecretRequest> requestEntity) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(processorServiceImpl.getSpace(requestEntity));
        } catch (MessageException messageException) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TopSecretResponse(HttpStatus.NOT_FOUND.value(), messageException.getMessage()));
        } catch (LocationException locationException) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TopSecretResponse(HttpStatus.NOT_FOUND.value(), locationException.getMessage()));
        }

    }

    @PostMapping(value = "/topsecretsplit/{satellite_name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topsecretsplit(@PathVariable(value = "satellite_name") String name, RequestEntity<TopSecretSplitRequest> requestEntity) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(processorServiceImpl.addSatelite(name, requestEntity));
        } catch (SateliteException sateliteException) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TopSecretResponse(HttpStatus.NOT_FOUND.value(), sateliteException.getMessage()));
        }
    }

    @GetMapping(value = "/topsecretsplit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topsecretsplit() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(processorServiceImpl.getSpace());
        } catch (QuasarException quasarException) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TopSecretResponse(HttpStatus.NOT_FOUND.value(), quasarException.getMessage()));
        } catch (MessageException messageException) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TopSecretResponse(HttpStatus.NOT_FOUND.value(), messageException.getMessage()));
        } catch (LocationException locationException) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TopSecretResponse(HttpStatus.NOT_FOUND.value(), locationException.getMessage()));
        }
    }

}
