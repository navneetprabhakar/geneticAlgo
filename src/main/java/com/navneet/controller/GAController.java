package com.navneet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.navneet.models.Fitness;
import com.navneet.models.Request;
import com.navneet.models.Response;
import com.navneet.models.SalesManRequest;
import com.navneet.service.GAService;

@RestController
public class GAController {
	@Autowired private GAService gaService;
	
	@PostMapping("salesManRoute")
	public Response<Fitness> findBestRoute(@RequestBody Request<SalesManRequest> request){
		return gaService.findBestRoute(request.getData());
	}
}
