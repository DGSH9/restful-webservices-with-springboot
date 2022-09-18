package com.dgsh.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue getAllBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",simpleBeanPropertyFilter);
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering_list")
	public MappingJacksonValue getALlList(){
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",simpleBeanPropertyFilter);
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
	}

}
