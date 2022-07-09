package com.example.examprepbattleship.web;

import com.example.examprepbattleship.model.binding.ShipAttackBindingModel;
import com.example.examprepbattleship.model.entity.Ship;
import com.example.examprepbattleship.model.service.ShipServiceModel;
import com.example.examprepbattleship.model.view.ShipViewModel;
import com.example.examprepbattleship.security.CurrentUser;
import com.example.examprepbattleship.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping()
    public String index(Model model){
        if(currentUser.getId() == 0){
            return "index";
        }
        List<ShipServiceModel> userShips = shipService.findAllShipsAddedByLoggedUser(currentUser.getId());
        List<ShipServiceModel> otherShips = shipService.findAllShipsAddedByOtherUser(currentUser.getId());
        List<ShipServiceModel> outputShips = shipService.getAllShipsOrdered();

        model.addAttribute("userShips", userShips);
        model.addAttribute("otherShips", otherShips);
        model.addAttribute("allShipsOrdered", outputShips);
        return "home";
    }

    @ModelAttribute
    public ShipAttackBindingModel shipAttackBindingModel(){
        return new ShipAttackBindingModel();
    }
/*
    @GetMapping("/attack")
    public String attack(Model model) {
        if(currentUser.getId() == 0)
            return "redirect:/";


        return "home";
    }
*/
    @PostMapping("/attack")
    public String attack(@Valid ShipAttackBindingModel shipAttackBindingModel){

        if(currentUser.getId() != 0){
        ShipServiceModel attackerShip = shipService.findShipById(shipAttackBindingModel.getAttackerId());
        ShipServiceModel defenderShip = shipService.findShipById(shipAttackBindingModel.getDefenderId());

        long attackerPower = attackerShip.getPower();
        long defenderHealth = defenderShip.getHealth();


            if(defenderHealth - attackerPower <= 0){
                shipService.deleteShipById(shipAttackBindingModel.getDefenderId());
            } else if(defenderHealth - attackerPower > 0) {
                defenderShip.setHealth(defenderHealth-attackerPower);
                shipService.editShip(defenderShip);
            }
        }


        return "redirect:/";

    }





}
