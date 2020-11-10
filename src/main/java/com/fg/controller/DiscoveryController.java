package com.fg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {

	@GetMapping("/{uid}")
	public ResponseEntity<String> getUser(@PathVariable("uid") Integer uid) {

		return ResponseEntity.ok("uid values is:"+uid);
	}

}
