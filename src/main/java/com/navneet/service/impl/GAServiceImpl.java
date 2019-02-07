package com.navneet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navneet.models.City;
import com.navneet.models.Fitness;
import com.navneet.models.Response;
import com.navneet.models.SalesManRequest;
import com.navneet.service.GAService;
import com.navneet.utils.CommonUtils;
@Service
public class GAServiceImpl implements GAService {
	private static Logger logger=LoggerFactory.getLogger(GAServiceImpl.class);
	@Autowired private CommonUtils commonUtils;
	@Override
	public Response<Fitness> findBestRoute(SalesManRequest request) {
		Response<Fitness> response=new Response<>();
		try {
			List<List<City>> population = commonUtils.generatePopulation(request.getCity(), request.getPopSize());
			List<Fitness> ranked=commonUtils.rankRoutes(population);
			ranked=Fitness.getNormalisedValue(ranked);
			Fitness bestRoute=ranked.get(0);
			for(int j=0;j<request.getGenSize();j++) {
				logger.info("Initiating generation:"+(j+1));
				List<Fitness> selection=commonUtils.selection(ranked, request.getPopSize()/3);
				List<Fitness> generation=new ArrayList<>();
				for(int i=0;i<selection.size()-1;i=i+2) {
					generation.add(commonUtils.crossOver(selection.get(i), selection.get(i+1)));
					generation.add(commonUtils.crossOver(selection.get(i+1), selection.get(i)));
				}
				generation=commonUtils.mutate(generation, request.getMutationRate());
				List<Fitness> genRanked=commonUtils.rankGenRoutes(generation);
				if(bestRoute.getDistance()>genRanked.get(0).getDistance()) {
					bestRoute=genRanked.get(0);
				}
				logger.info("Global Best route:"+commonUtils.printRoute(bestRoute));
			}
			response=new Response<>("success", commonUtils.printRoute(bestRoute), bestRoute);
		}catch(Exception e) {
			logger.error("An error occurred::",e);
			response=new Response<>("error", "Please try again", null);
		}
		return response;
	}

}
