package com.example.examprepbattleship.service.impl;

import com.example.examprepbattleship.model.entity.Ship;
import com.example.examprepbattleship.model.service.ShipServiceModel;
import com.example.examprepbattleship.model.view.ShipViewModel;
import com.example.examprepbattleship.repository.ShipRepository;
import com.example.examprepbattleship.security.CurrentUser;
import com.example.examprepbattleship.service.CategoryService;
import com.example.examprepbattleship.service.ShipService;
import com.example.examprepbattleship.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ShipRepository shipRepository;

    public ShipServiceImpl(ModelMapper modelMapper, CurrentUser currentUser, UserService userService, CategoryService categoryService, ShipRepository shipRepository) {
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
        this.shipRepository = shipRepository;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        Ship ship = modelMapper.map(shipServiceModel, Ship.class);
        ship.setUser(userService.findById(currentUser.getId()));
        ship.setCategory(categoryService.findByCategoryName(shipServiceModel.getCategory()));
        shipRepository.save(ship);
    }

    @Override
    public ShipServiceModel findShipByName(String name) {
        return modelMapper.map(shipRepository.findByName(name), ShipServiceModel.class);
    }

    @Override
    public List<ShipServiceModel> findAllShipsAddedByLoggedUser(long id) {
        return shipRepository
                .findAllByUserId(id)
                .stream()
                .map(ships -> modelMapper.map(ships, ShipServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipServiceModel> findAllShipsAddedByOtherUser(long id) {
        return shipRepository
                .findAllByUserIdIsNot(id)
                .stream()
                .map(ships -> modelMapper.map(ships, ShipServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShipServiceModel findShipById(long attackerId) {
        return modelMapper.map(shipRepository.findById(attackerId), ShipServiceModel.class);
    }

    @Override
    public void deleteShipById(long defenderId) {
        shipRepository.deleteById(defenderId);
    }

    @Override
    public List<ShipServiceModel> getAllShipsOrdered() {
        return shipRepository
                .getAllBy()
                .stream()
                .map(ships -> modelMapper.map(ships, ShipServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editShip(ShipServiceModel defenderShip) {
        Ship currentShip = shipRepository.findById(defenderShip.getId());
        currentShip.setHealth(defenderShip.getHealth());
        shipRepository.deleteById(defenderShip.getId());
        shipRepository.save(currentShip);
    }

}
