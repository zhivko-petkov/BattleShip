package com.example.examprepbattleship.service;

import com.example.examprepbattleship.model.service.ShipServiceModel;
import com.example.examprepbattleship.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(ShipServiceModel shipServiceModel);

    ShipServiceModel findShipByName(String name);

    List<ShipServiceModel> findAllShipsAddedByLoggedUser(long id);

    List<ShipServiceModel> findAllShipsAddedByOtherUser(long id);

    ShipServiceModel findShipById(long attackerId);

    void deleteShipById(long defenderId);

    List<ShipServiceModel> getAllShipsOrdered();

    void editShip(ShipServiceModel defenderShip);
}
