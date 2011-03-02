package com.neotechnology.cineasts.domain;

import org.springframework.data.graph.annotation.NodeEntity;

@NodeEntity
public class Movie {

	@Override
	public String toString() {
		return "I am a Movie object";
	}
}
