package com.vehicleRental.clientside.controller;

import com.vehicleRental.clientside.models.Vehicle;
import com.vehicleRental.clientside.models.VehicleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;


import javax.validation.Valid;
import java.util.List;




@Controller
public class VehicleController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Return vehicles List.
     *
     * @return L'URL .
     */
    @GetMapping("/vehicles/list")
    public String getAll(Model model) {

        //get all vehicle
        List<Vehicle> vehicles = restTemplate.getForObject("http://API-REST-SERVICE/api/v1/vehicles", List.class);

        model.addAttribute("vehicles", vehicles);
        return "vehicleList";
    }

    /**
     * affiche le vehicule
     *
     * @param id selon id du vehicule.
     */
    @GetMapping(value = "/vehicles/{id}")
    public String getOne(Model model, @PathVariable int id) {

        //get one vehicle
        Vehicle vehicles = restTemplate.getForObject("http://api-rest-service/api/v1/vehicles/" + id, Vehicle.class);

        model.addAttribute("vehicles", vehicles);
        return "vehicleList";
    }

    /**
     * renvoie le formulaire.
     *
     * @since 3.0
     */
    @GetMapping(value = "/vehicles/new")
    public String form(Model model) {

        model.addAttribute("vehicleForm", new VehicleForm());
        return "vehicleForm";
    }

    /**
     * Valide le formulaire
     *
     * @param vehicleForm et return results.
     */
    @PostMapping(value = "vehicles/new")
    public String checkVehicleInfo(@Valid VehicleForm vehicleForm, BindingResult bindingResult, Model model) {
        model.addAttribute("vehicleForm", vehicleForm);
        System.out.println(vehicleForm);
        if (bindingResult.hasErrors()) {
            return "vehicleForm";
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleForm.getBrand());
        vehicle.setType(vehicleForm.getType());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity <Vehicle> requestEntity = new HttpEntity<>(vehicle, headers);

        restTemplate.postForObject("http://api-rest-service/api/v1/vehicles", requestEntity, Vehicle.class );
        return "redirect:/vehicles/list";
    }

//    @PostMapping(value = "vehicles/save")
//    public String save(@Valid VehicleForm vehicleForm, BindingResult bindingResult, Model model) {
//        model.addAttribute("vehicleForm", vehicleForm);
//        if (bindingResult.hasErrors()) {
//            return "vehicleForm";
//        }
//
//        return "redirect:/results";
//    }
}


