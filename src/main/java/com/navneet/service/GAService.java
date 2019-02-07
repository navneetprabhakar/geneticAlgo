package com.navneet.service;

import com.navneet.models.Fitness;
import com.navneet.models.Response;
import com.navneet.models.SalesManRequest;

public interface GAService {
	public Response<Fitness> findBestRoute(SalesManRequest request);
}
