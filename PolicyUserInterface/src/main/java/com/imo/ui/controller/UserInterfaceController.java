package com.imo.ui.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.imo.ui.modal.PolicyMaster;
import com.imo.ui.modal.User;
import com.imo.ui.serrvice.LoginService;

@Controller
public class UserInterfaceController {

	@Autowired
	private LoginService loginService;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping({ "/login", "/" })
	public ModelAndView showLoginpage(@ModelAttribute("login") User user, ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		modelAndView.getModel().put("msg", "Please Provide Your Credentials");
		return modelAndView;
	}

	@GetMapping("/consumerDetails")
	public String consumerDetails() {
		return "consumerDetails";
	}
	
	@GetMapping("/viewConsumer")
	public String viewConsumerDetails() {
		return "viewConsumer";
	}
	
	@GetMapping("/policyDetails")
	public String policyDetails() {
		return "policyDetails";
	}
	@GetMapping("/viewPolicy")
	public String viewPolicyDetails() {
		return "viewPolicy";
	}
	
	
	
	@PostMapping("/index")
	public ModelAndView checkLoginDetails(@ModelAttribute("login") User user, BindingResult result,
			ModelAndView modelAndView) {

		if (loginService.validate(user)) {
			modelAndView.setViewName("index");
			modelAndView.getModel().put("name", user.getUserName());
			modelAndView.getModel().put("userId", "1");
			return modelAndView;
		}

		modelAndView.setViewName("login");
		modelAndView.getModel().put("msg", "Sorry!! Invalid Credentials. Try again");

		return modelAndView;
	}
	
	@PostMapping("/createPolicy")
	public ModelAndView createPolicy(@ModelAttribute("createPolicy") PolicyMaster policyMaster, BindingResult result, ModelAndView modelAndView) {
			
		String restApi = "localhost:8200/createPolicy";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("property_type", "a");
		jsonObject.put("consumer_type", "b");
		jsonObject.put("assured_sum", "c");
		jsonObject.put("tenure", "d");
		jsonObject.put("businessValue", 1);
		jsonObject.put("propertyValue", 2);
		jsonObject.put("base_location", "e");
		jsonObject.put("type", "f");
		
		HttpEntity<String> request = 
				new HttpEntity<String>(jsonObject.toString(), requestHeaders);
		
		String response = restTemplate.postForObject(restApi, request, String.class);
		
		modelAndView.setViewName("index");
		
		return modelAndView;
	}

}
