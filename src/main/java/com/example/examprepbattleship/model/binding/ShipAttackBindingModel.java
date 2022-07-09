package com.example.examprepbattleship.model.binding;

public class ShipAttackBindingModel {
    private long attackerId;
    private long defenderId;

    public ShipAttackBindingModel() {
    }

    public long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(long attackerId) {
        this.attackerId = attackerId;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(long defenderId) {
        this.defenderId = defenderId;
    }
}
