package com.geektrust.backend.repositories;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;

public interface IMetroCardRepository {
    public MetroCard save(MetroCard metroCard);
    public Optional<MetroCard> getMetroCard(String metroCardId);
}
