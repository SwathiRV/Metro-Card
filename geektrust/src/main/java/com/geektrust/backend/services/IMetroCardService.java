package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;

public interface IMetroCardService {
    public MetroCard create(String metroCardId, float balance);
    public MetroCard update(String metroCardId, float balance);
}
