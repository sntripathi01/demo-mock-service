package com.demo.mocko.service.model;

import java.util.HashMap;
import java.util.Map;

public class ServiceResponse {

	private String success;
	private String error;
	private String status;
	private Map<String, Object> data;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> getData() {
		if (null == data) {
			data = new HashMap<String, Object>();
		}
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}