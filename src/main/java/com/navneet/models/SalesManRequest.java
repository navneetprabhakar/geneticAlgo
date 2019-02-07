package com.navneet.models;

import java.util.List;

import lombok.Data;

@Data
public class SalesManRequest {
	private List<City> city;
	private Integer popSize;
	private Integer genSize;
	private Double mutationRate;
}
