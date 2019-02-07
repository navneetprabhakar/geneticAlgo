package com.navneet.models;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fitness {
	private Double cumSum;
	private Double normalised;
	private Double distance;
	private List<City> route;
	
	public Fitness(Double distance, List<City> route) {
		this.distance = distance;
		this.route = route;
	}
	public static Fitness setNormalised(Fitness fitness, Double cumSum) {
		fitness.setNormalised(fitness.getCumSum()/cumSum);
		return fitness;
	}
	/**
	 * This method calculates the route distance
	 * 
	 * @return
	 */
	public static Fitness routeDistance(Fitness fitness) {
		if (fitness.getDistance() == 0.0) {
			Double pathDistance = 0.0;
			for (int i = 0; i < fitness.getRoute().size(); i++) {
				City fromCity = fitness.getRoute().get(i);
				City toCity = i + 1 < fitness.getRoute().size() ? fitness.getRoute().get(i + 1) : fitness.getRoute().get(0);
				pathDistance += fromCity.distance(toCity);
			}
			fitness.setDistance(pathDistance);
		}
		return fitness;
	}
	
	public static List<Fitness> sort(List<Fitness> fitnessList) {
		Comparator<Fitness> comparator = new Comparator<Fitness>() {
			@Override
			public int compare(Fitness o1, Fitness o2) {
				return o1.getDistance().compareTo(o2.getDistance());
			}
		};
		fitnessList.sort(comparator);
		return fitnessList;
	}
	
	public static List<Fitness> getNormalisedValue(List<Fitness> fitnessList){
		Double cumSum=0.0;
		for(Fitness fitness:fitnessList) {
			cumSum+=fitness.getDistance();
			fitness.setCumSum(cumSum);
		}
		final Double sum=cumSum;
		fitnessList=fitnessList.stream().map(e-> setNormalised(e, sum)).collect(Collectors.toList());
		return fitnessList;
	}
}
