package com.navneet.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.navneet.models.City;
import com.navneet.models.Fitness;
@Component
public class CommonUtils {

	/**
	 * This method is used to generate random population for initial step
	 * @param cityList
	 * @param popSize: Population size
	 * @return
	 */
	public List<List<City>> generatePopulation(List<City> cityList, int popSize) {
		List<List<City>> population = new ArrayList<>();
		for (int i = 0; i < popSize; i++) {
			List<City> route = new ArrayList<>();
			Random random = new Random();
			while (route.size() < cityList.size()) {
				Integer x = random.nextInt(cityList.size());
				if (!route.contains(cityList.get(x))) {
					route.add(cityList.get(x));
				}
			}
			population.add(route);
		}
		return population;
	}
	
	/** 
	 * This method is used to rank routes for initial population
	 * @param population
	 * @return
	 */
	public List<Fitness> rankRoutes(List<List<City>> population){
		List<Fitness> rankedFitness=new ArrayList<>();
		for(List<City> route:population) {
			Fitness fitness=new Fitness(0.0, route);
			fitness=Fitness.routeDistance(fitness);
			rankedFitness.add(fitness);
		}
		rankedFitness = Fitness.sort(rankedFitness);
		return rankedFitness;
	}
	
	/**
	 * This method is used to rank generations
	 * @param generation
	 * @return
	 */
	public List<Fitness> rankGenRoutes(List<Fitness> generation){
		List<Fitness> rankedFitness=new ArrayList<>();
		for(int i=0;i<generation.size();i++) {
			List<City> route= generation.get(i).getRoute();
			Fitness fitness=new Fitness(0.0, route);
			fitness=Fitness.routeDistance(fitness);
			rankedFitness.add(fitness);
		}
		rankedFitness = Fitness.sort(rankedFitness);
		return rankedFitness;
	}
	
	/**
	 * This method is used to print route
	 * @param route
	 */
	public String printRoute(Fitness route) {
		String output="";
		for(int i=0;i<route.getRoute().size();i++) {
			City city=route.getRoute().get(i);
			if(i==route.getRoute().size()-1) {
				output=output.concat(city.getName()+" ->"+route.getDistance());
			}else {
				output=output.concat(city.getName()+"->");
			}
		}
		return output;
	}
	
	/**
	 * This method is used to select routes for next generation
	 * @param ranked
	 * @param eliteSize
	 * @return
	 */
	public List<Fitness> selection(List<Fitness> ranked, int eliteSize) {
		List<Fitness> selectedList = new ArrayList<>();
		for (int i = 0; i < eliteSize; i++) {
			selectedList.add(new Fitness(0.0, ranked.get(i).getRoute()));
		}
		Random random = new Random();
		while(selectedList.size()<=ranked.size()) {
			Double pick = 100 * random.nextDouble();
			for (int j = 0; j < ranked.size(); j++) {
				if (pick <= ranked.get(j).getNormalised()) {
					selectedList.add(new Fitness(0.0, ranked.get(j).getRoute()));
					break;
				}
			}
		}
		return selectedList;
	}

	/**
	 * This method is used to Crossover 2 routes
	 * @param parent1
	 * @param parent2
	 * @return
	 */
	public Fitness crossOver(Fitness parent1, Fitness parent2) {
		int point1 = parent1.getRoute().size() / 3;
		int point2 = (point1 * 2) + 1;
		List<City> childRoute = new ArrayList<>();
		List<City> portion1 = new ArrayList<>();
		List<City> portion2 = new ArrayList<>();
		List<City> portion3 = new ArrayList<>();
		for (int i = point1; i < point2; i++) {
			portion1.add(parent1.getRoute().get(i));
		}
		for (int i = point2; i < parent2.getRoute().size(); i++) {
			for (int j = 0; j < parent2.getRoute().size(); j++) {
				if (!portion1.contains(parent2.getRoute().get(j)) && !portion2.contains(parent2.getRoute().get(j))) {
					portion2.add(parent2.getRoute().get(j));
					break;
				}
			}
		}
		for (int i = 0; i < point1; i++) {
			for (int j = 0; j < parent1.getRoute().size(); j++) {
				if (!portion1.contains(parent1.getRoute().get(j)) && !portion2.contains(parent1.getRoute().get(j))
						&& !portion3.contains(parent1.getRoute().get(j))) {
					portion3.add(parent1.getRoute().get(j));
					break;
				}
			}
		}
		childRoute.addAll(portion3);
		childRoute.addAll(portion1);
		childRoute.addAll(portion2);
		Fitness child=new Fitness(0.0, childRoute);
		child=Fitness.routeDistance(child);
		return child;
	}
	
	/**
	 * This method is used to mutate a portion of population
	 * @param generation
	 * @param mutationRate
	 * @return
	 */
	public List<Fitness> mutate(List<Fitness> generation, Double mutationRate){
		Double mutation=mutationRate*generation.size();
		System.out.println("Mutating total routes:"+mutation.intValue());
		Random random=new Random();
		for(int i=0;i<mutation.intValue();i++) {
			Integer pick = random.nextInt(generation.size());
			Fitness route=generation.get(pick);
			route=mutateRoute(route);
			generation.set(pick, route);
		}
		return generation;
	}
	
	/**
	 * This method is used to mutate individual route
	 * @param route
	 * @return
	 */
	public Fitness mutateRoute(Fitness route) {
		Random random=new Random();
		Integer pick = random.nextInt(route.getRoute().size());
		Integer pick2 = route.getRoute().size()-1-pick;
		City A=route.getRoute().get(pick);
		City B=route.getRoute().get(pick2);
		route.getRoute().set(pick, B);
		route.getRoute().set(pick2, A);
		return route;
	}
	
	
}
