package org.example.restcilentdemo.service;

import org.example.restcilentdemo.dto.AgifyResponse;
import org.example.restcilentdemo.dto.GenderizeResponse;
import org.example.restcilentdemo.dto.NationalizeResponse;
import org.example.restcilentdemo.dto.PersonDataResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;


@Service
public class PersonDataService {

    private final RestClient restClient;

    private final Map<String,PersonDataResponse> cache = new HashMap<>();

    public PersonDataService(RestClient restClient){
        this.restClient = restClient;
    }

    public PersonDataResponse getPersonData(String firstName, String middleName,String lastName ){

        String cacheKey = firstName +" " + middleName +" "+ lastName;

        if(cache.containsKey(cacheKey)){
            return cache.get(cacheKey);
        }
        System.out.println("Kalder eksterne API'er for: " + cacheKey);

        AgifyResponse agify = restClient.get()
                .uri("https://api.agify.io?name={name}", firstName)
                .retrieve()
                .body(AgifyResponse.class);

        GenderizeResponse genderize = restClient.get()
                .uri("https://api.genderize.io?name={name}", firstName)
                .retrieve()
                .body(GenderizeResponse.class);

        NationalizeResponse nationalize = restClient.get()
                .uri("https://api.nationalize.io?name={name}", firstName)
                .retrieve()
                .body(NationalizeResponse.class);

        String name;
        if (middleName != null && !middleName.isBlank()) {
            name = firstName + " " + middleName + " " +lastName;
        } else  {
            name = firstName + " " + lastName;
        }
        String country = null;
        Double countryProbability = null;
        if (nationalize != null && nationalize.country() != null && !nationalize.country().isEmpty()) {
            NationalizeResponse.Country top = nationalize.country().get(0);
            country = top.country_id();
            countryProbability = top.probability();

        }
        PersonDataResponse response = new PersonDataResponse(
                name,
                firstName,
                middleName,
                lastName,
                genderize != null ? genderize.gender() : null,
                genderize != null ? genderize.probability() : null,
                agify != null ? agify.age() : null,
                null,
                country,
                countryProbability
        );

        cache.put(cacheKey, response);
        return response;
}



}
