package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Optional;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.MetroCard;

public class MetroCardRepository implements IMetroCardRepository{

    private final HashMap<String, MetroCard> metroCardRepository;
    private int autoincrement;

    public MetroCardRepository() {
        this.metroCardRepository = new HashMap<>();
        this.autoincrement = Constants.ZERO;
    }

    @Override
    public MetroCard save(MetroCard metroCard) {
        if(metroCard.getId()==Constants.NULL){
            autoincrement++;
            MetroCard tMetroCard = new MetroCard(Integer.toString(autoincrement), metroCard.getBalance());
            metroCardRepository.put(tMetroCard.getId(), tMetroCard);
            return tMetroCard;
        }
        metroCardRepository.put(metroCard.getId(),metroCard);
        return metroCard;
    }

    @Override
    public Optional<MetroCard> getMetroCard(String metroCardId){
        return Optional.ofNullable(metroCardRepository.get(metroCardId));
    }
    
}
