package com.navneet.models;

import lombok.Data;

@Data
public class Request<T> {
	private T data;
}
