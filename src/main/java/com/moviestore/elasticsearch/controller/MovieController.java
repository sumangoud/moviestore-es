package com.moviestore.elasticsearch.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.elasticsearch.dto.MovieDto;
import com.moviestore.elasticsearch.model.Movie;
import com.moviestore.elasticsearch.service.MovieServiceImpl;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieServiceImpl movieService;

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@RequestBody MovieDto movieDto) {
		Movie movie=new Movie();
		movie.setId(movieDto.getId());
		movie.setName(movieDto.getName());
		movie.setLanguage(movieDto.getLanguage());
		movie.setRating(movieDto.getRating());
		return movieService.save(movie);
	}
	
	@RequestMapping(value="/search/{name}",method = RequestMethod.GET)
	public String findOne(@PathVariable String name) {
		return movieService.findByName(name);
	}
	
	@RequestMapping(value="/findAll",method = RequestMethod.GET)
	public String findAll() {
		return movieService.findAll();
	}

}
