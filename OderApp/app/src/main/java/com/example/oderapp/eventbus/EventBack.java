package com.example.oderapp.eventbus;

import com.example.oderapp.model.Address;

public class EventBack {
    private Address address;

    public EventBack(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
