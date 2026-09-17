package org.example.restcilentdemo.dto;

import java.util.List;

public record NationalizeResponse(String name, List<Country> country) {

    public record Country(String country_id, Double probability) {
    }
}