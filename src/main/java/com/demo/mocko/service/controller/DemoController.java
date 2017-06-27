package com.demo.mocko.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mocko.service.DemoService;
import com.demo.mocko.service.model.VersionResponse;

@RestController
public class DemoController {
	@Autowired
	@Qualifier("DemoServiceImpl")
	private DemoService demoService;

	@RequestMapping("/version")
	public VersionResponse getVersion(@RequestParam String version) {
		return demoService.getVersion(version);
	}

}
