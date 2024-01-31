package com.geektrust.backend.entities;

public class MetroCard extends BaseEntity{

    private float balance;

    public MetroCard(String id, float balance) {
        this.id = id;
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
/*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MetroCard other = (MetroCard) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
*/
    @Override
    public String toString() {
        return "MetroCard [id=" + id + " balance=" + balance + "]";
    }   
    
}
