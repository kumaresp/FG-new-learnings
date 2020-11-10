package com.fg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fg.model.InlineObject2;
import com.fg.model.InlineResponse2002;
import com.fg.service.TokensService;

//   http://localhost:8813/USERSSERVICE/V1/issueToken/validatetoken
@RequestMapping("/USERSSERVICE/V1")
@RestController            
public class TokensController {

	@Autowired
	private TokensService tokenService;

	@RequestMapping(value = "/issueToken/validatetoken", method = RequestMethod.POST,
			        produces = {"application/json"},
			        consumes = {"application/x-www-form-urlencoded"})
	public ResponseEntity<InlineResponse2002> validateAccessToken(InlineObject2 inlineObject) {

		String x_api_key=null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	    if (requestAttributes instanceof ServletRequestAttributes) {
	        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
	        x_api_key=request.getHeader("x-api-key");	     
	    }
		
		return ResponseEntity.ok(tokenService.validateAccessToken(inlineObject,x_api_key));
	}

	@GetMapping("/getR")
	public String get() {
		
		return "hello";
	}
	
}
