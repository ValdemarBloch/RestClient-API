package org.example.restcilentdemo.dto;

public record PersonDataResponse(
    String fullName,
    String firstName,
    String middleName,
    String lastName,
    String gender,
    Double genderProbability,
    Integer age,
    Double ageProbability,
    String country,
    Double countryProbability)
{}

