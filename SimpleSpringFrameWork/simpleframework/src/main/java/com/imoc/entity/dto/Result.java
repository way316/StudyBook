package com.imoc.entity.dto;

import lombok.Data;

@Data
public class Result<T> {
	//state codes, 200 means return success.
	private int code;
	//description of the return result
	private String msg;
	//Return the result
	private T data;
}