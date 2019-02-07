package com.navneet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
	private String name;
	private Double x;
	private Double y;

	/**
	 * This method finds distance between 2 cities
	 * 
	 * @param city
	 * @return
	 */
	public Double distance(City city) {
		Double xDis = Math.abs(this.x - city.getX());
		Double yDis = Math.abs(this.y - city.getY());
		return Math.sqrt((xDis * xDis) + (yDis * yDis));
	}
}
