package com.co.meli.quasar.service;

import com.co.meli.quasar.exception.MessageException;

import java.util.List;

public interface IMessageService {
    String getMessage(List<List<String>> messages) throws MessageException;
}
