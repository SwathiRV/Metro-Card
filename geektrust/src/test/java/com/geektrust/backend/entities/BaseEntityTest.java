package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Base Entity Test")
public class BaseEntityTest {

    @Test
    public void getIdTest(){
        BaseEntity baseEntity = new BaseEntity();
        assertNull(baseEntity.getId());
    }
    
}
