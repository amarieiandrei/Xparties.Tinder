package com.demo.Xparties.Tinder.Exception.EventException;

public class EventAlreadyContainsPerson extends RuntimeException {

    public EventAlreadyContainsPerson(String message) {
        super(message);
    }
}
