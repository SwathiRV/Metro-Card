package com.geektrust.backend.utility;

import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;

public class PassengerUtility {
    
    public static float getDiscount(float ride_cost){
        return ride_cost * Constants.DISCOUNT;
    }

    public static float getPay(float ride_cost, float discount, float balance){
        ride_cost  = rideCost(ride_cost, discount);
        if(inSufficientBalance(balance, ride_cost)){
            return ride_cost;
        }else{
            return ride_cost + getTax(ride_cost, balance);
        }
    }

    // actual_balance , actual_ride_cost , discount_given -> update the metrocard balance
    public static float getBalance(float ride_cost, float discount, float balance){

        if(inSufficientBalance(balance, ride_cost)){
            balance -= rideCost(ride_cost, discount);
        }else{
            balance = Constants.ZERO;
        }
        return balance;
    }


    public static float findDiscount(CheckStatus checkStatus, String passengerType){
        if(checkStatus!=CheckStatus.CHECK_OUT){
            return 0;
        }
        return PassengerUtility.getDiscount(PassengerType.valueOf(passengerType).getPassengerCost());
    }

    private static float getTax(float ride_cost, float balance){
        return (ride_cost - balance) * Constants.TAX;
    }

    private static float rideCost(float ride_cost, float discount){
        return ride_cost-=discount;
    }

    private static boolean inSufficientBalance(float balance, float ride_cost){
        if(balance>=ride_cost){
            return true;
        }
        return false;
    }

}
