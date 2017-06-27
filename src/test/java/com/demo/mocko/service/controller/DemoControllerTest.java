package com.demo.mocko.service.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.demo.mocko.service.impl.DemoServiceImpl;
import com.demo.mocko.service.model.ServiceResponse;
import com.demo.mocko.service.model.VersionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {

	@InjectMocks
	private DemoController demoController;

	private MockMvc mockMvc;

	@Mock
	private RestTemplate mockRestTemplate;

	@Spy
	private DemoServiceImpl demoService;

	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(demoController).build();
		ReflectionTestUtils.setField(demoService, "restTemplate", mockRestTemplate);
	}

	@Test
	public void testGetVersion() throws Exception {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setStatus("1");
		Mockito.when(mockRestTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(serviceResponse);
		String responseString = mockMvc.perform(get("/version?version=1").contentType("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		VersionResponse response = new ObjectMapper().readValue(responseString, VersionResponse.class);
		assertEquals(null, response.getCurrentVersion());
	}

}
