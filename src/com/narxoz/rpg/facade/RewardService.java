package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "Error";
        }
        if (battleResult.getWinner().contains("Warrior"))return "500gold + Legendary sword";
        else return "No items";
    }
}
