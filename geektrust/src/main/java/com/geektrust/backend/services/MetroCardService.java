package com.geektrust.backend.services;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.NegativeBalanceException;
import com.geektrust.backend.exceptions.NoMetroCardFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;

public class MetroCardService implements IMetroCardService{
    
    private final IMetroCardRepository metroCardRepository;

    public MetroCardService(IMetroCardRepository metroCardRepository) {
        this.metroCardRepository = metroCardRepository;
    }

    public MetroCard create(String metroCardId, float balance){
        if(balance<0){
            throw new NegativeBalanceException("balance cannot be negative");
        }
        return metroCardRepository.save(new MetroCard(metroCardId, balance));
    }

    public MetroCard update(String metroCardId, float balance){
        Optional<MetroCard> metro = metroCardRepository.getMetroCard(metroCardId);
        if(metro.isPresent()){
            metro.get().setBalance(balance);
            return metroCardRepository.getMetroCard(metroCardId).get();
        }
        throw new NoMetroCardFoundException("MetroCard not Found");
    }

}
