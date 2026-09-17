package org.example.restcilentdemo.controller;


import org.example.restcilentdemo.dto.PersonDataResponse;
import org.example.restcilentdemo.service.PersonDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonDataController {

    private PersonDataService personDataService;

    public PersonDataController(PersonDataService personDataService) {
            this.personDataService = personDataService;
    }
    @GetMapping("/persondata")
    public PersonDataResponse getPersonData(
            @RequestParam String firstName,
            @RequestParam(required = false) String middleName,
            @RequestParam String lastName) {

        return personDataService.getPersonData(firstName, middleName, lastName);
    }
}
