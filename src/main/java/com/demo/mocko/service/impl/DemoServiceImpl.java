package com.demo.mocko.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.mocko.service.DemoService;
import com.demo.mocko.service.model.ServiceResponse;
import com.demo.mocko.service.model.VersionResponse;

@Component
public class DemoServiceImpl implements DemoService {

	@Autowired
	private RestTemplate restTemplate;

	@Value(value = "${service.url}")
	private String url;

	@SuppressWarnings("rawtypes")
	@Override
	public VersionResponse getVersion(String version) {
		VersionResponse versionResponse = new VersionResponse();
		ServiceResponse response = restTemplate.getForObject(url+"/version", ServiceResponse.class);
		if ("0".equals(response.getStatus())) {

			versionResponse
					.setCurrentVersion(((Map) response.getData().get("version")).get("currentVersion").toString());
			versionResponse.setStableVersion(((Map) response.getData().get("version")).get("stableVersion").toString());
			versionResponse.setNeedToUpdate(Integer.parseInt(version) < Integer
					.parseInt(((Map) response.getData().get("version")).get("stableVersion").toString()) ? true
							: false);
		}

		return versionResponse;
	}

}
