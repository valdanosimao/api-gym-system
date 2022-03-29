package com.api.gym.controllers;

import com.api.gym.dtos.CustomersDTO;
import com.api.gym.services.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gym")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomersDTO>> getCustomers () {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customersService.getAllCustomers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<CustomersDTO>> getCustomersById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(customersService.getCustomersById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<CustomersDTO> addCustomers(@RequestBody CustomersDTO customersDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customersService.saveCustomers(customersDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomersDTO> updateCustomers(@RequestBody CustomersDTO customersDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(customersService.updateCustomers(customersDTO, id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
            customersService.deleteCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfylly");
    }

}
