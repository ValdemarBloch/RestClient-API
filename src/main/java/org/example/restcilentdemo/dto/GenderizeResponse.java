package org.example.restcilentdemo.dto;

public record GenderizeResponse(String name, String gender, Double probability, Integer count) {
}