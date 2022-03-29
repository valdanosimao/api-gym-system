package com.api.gym.services;

import com.api.gym.dtos.CustomersDTO;
import com.api.gym.entities.Customers;
import com.api.gym.exceptions.ResourceNotFoundException;
import com.api.gym.repositories.CustomersRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class CustomersService {

    private final CustomersRepository customersRepository;

    public CustomersService(CustomersRepository gymRepository) {
        this.customersRepository = gymRepository;
    }

    /**
     * method that returns all customers
     * @return all customers
     */
    public List<CustomersDTO> getAllCustomers(){

        List<Customers> customer = customersRepository.findAll();

        return customer.stream()
                .map(customers -> new ModelMapper().map(customers, CustomersDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * method that returns a customer found by id.
     * @param id of the client that will be found.
     * @return a customer if it exists.
     */
    public Optional<CustomersDTO> getCustomersById(Long id){
        //getting customer by id
        Optional<Customers> customers = customersRepository.findById(id);

        //if not found throw an exception
        if(customers.isEmpty()) {
            throw new ResourceNotFoundException("Product by id " + id + " not found ");
        }

        //converting my optional custumers into CustumersDTO
        CustomersDTO dto = new ModelMapper().map(customers.get(), CustomersDTO.class);

        //creating and returning an optional from CustomersDTO
        return Optional.of(dto);
    }

    /**
     * method that saves a customer
     * @param customersDTO parameter
     * @return customer who will be saved
     */
    public CustomersDTO saveCustomers(CustomersDTO customersDTO){

        //create a mapping object.
        ModelMapper mapper = new ModelMapper();

        //convert the customersDto to a customers
        Customers customers = mapper.map(customersDTO, Customers.class);

        //save customer in database
        customers = customersRepository.save(customers);

        customersDTO.setId(customers.getId());

        //returns the updated customers
        return customersDTO;
    }

    /**
     * method that deletes a customer by id
     * @param id customer id to be deleted
     */
    public void deleteCustomerById(Long id){

        //check if the customer exists
        Optional<Customers> customers = customersRepository.findById(id);

        //if not, throw an exception
        if(customers.isEmpty()){
            throw new ResourceNotFoundException("Customer not found by id " + id + " customers not exist");
        }

        //delete the customer by id
        customersRepository.deleteById(id);
    }

    /**
     * method to update the customer in the list
     * @param customersDTO that will be updated
     * @param id client id that will be updated
     * @return returns the customer, after updating the list
     */
    public CustomersDTO updateCustomers(CustomersDTO customersDTO, Long id){

        //pass the id to the customerDTO
        customersDTO.setId(id);

        //create a mapping object
        ModelMapper mapper = new ModelMapper();

        //convert customerDTO to customer
        Customers customers = mapper.map(customersDTO, Customers.class);

        //save the customer to database
        customersRepository.save(customers);

        //return customerDTO
        return customersDTO;
    }

}
