package com.example.examprepbattleship.web;

import com.example.examprepbattleship.model.binding.ShipAddBindingModel;
import com.example.examprepbattleship.model.service.ShipServiceModel;
import com.example.examprepbattleship.security.CurrentUser;
import com.example.examprepbattleship.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public ShipController(ShipService shipService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String add(Model mod) {
        if(currentUser.getId() == 0)
            return "redirect:/";

        mod.addAttribute("shipNameError", false);

        return "ship-add";
    }

    @ModelAttribute
    public ShipAddBindingModel shipAddBindingModel(){
        return new ShipAddBindingModel();
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ShipAddBindingModel shipAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if(currentUser.getId() != 0){
            ShipServiceModel checkShip = shipService.findShipByName(shipAddBindingModel.getName());

            if(bindingResult.hasErrors() || checkShip.getId() != 0){
                redirectAttributes.addFlashAttribute("shipAddBindingModel", shipAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddBindingModel", bindingResult);
                redirectAttributes.addFlashAttribute("shipNameError", true);

                return "redirect:add";
            }
            shipService.addShip(modelMapper.map(shipAddBindingModel,
                    ShipServiceModel.class));
        }




        return "redirect:/";

    }


}
