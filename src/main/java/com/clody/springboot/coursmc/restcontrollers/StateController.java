package com.clody.springboot.coursmc.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clody.springboot.coursmc.models.City;
import com.clody.springboot.coursmc.models.State;
import com.clody.springboot.coursmc.models.dto.CityDto;
import com.clody.springboot.coursmc.models.dto.StateDto;
import com.clody.springboot.coursmc.services.ICityService;
import com.clody.springboot.coursmc.services.IStateService;

@RestController
@RequestMapping("/api")
public class StateController {
	
	@Autowired
	private IStateService stateService;
	
	@Autowired
	private ICityService cityService;
	
	@GetMapping("/states")
	public ResponseEntity<List<StateDto>> findAll() {
		List<State> states = stateService.findAll();
		List<StateDto> statesDto = states
				.stream().map(state -> new StateDto(state))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(statesDto);
	}
	@GetMapping("/states/{stateId}/cities")
	public ResponseEntity<List<CityDto>> findCities(@PathVariable Integer stateId) {
		List<City> cities = cityService.findCities(stateId);
		List<CityDto> citiesDto = cities
				.stream().map(city -> new CityDto(city))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(citiesDto);
	}
}
