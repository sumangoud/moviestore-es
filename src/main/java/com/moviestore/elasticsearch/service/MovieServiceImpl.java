package com.moviestore.elasticsearch.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviestore.elasticsearch.model.Movie;
import com.moviestore.elasticsearch.util.Jsonutil;

@Service
public class MovieServiceImpl {

	@Autowired
	RestClient restClient;

	public String save(Movie m) {

		String out = null;
		try {
			Response response = restClient.performRequest("POST", "/moviestore/movie",
					Collections.<String, String> emptyMap(), new NStringEntity(Jsonutil.toJsonObj(m)));
			out = EntityUtils.toString(response.getEntity());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return out;
	}

	public String findByName(String name) {
		String out = null;
		try {
			Response response = restClient.performRequest("GET", "/moviestore/movie/_search/?q=name:" + name,
					Collections.<String, String> emptyMap());
			out = EntityUtils.toString(response.getEntity());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return out;
	}

	public String findAll() {
		String out = null;
		try {
			Response response = restClient.performRequest("GET", "/moviestore/movie/_search?name=true&q=*:*",
					Collections.<String, String> emptyMap());
			out = EntityUtils.toString(response.getEntity());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return out;
	}
}
