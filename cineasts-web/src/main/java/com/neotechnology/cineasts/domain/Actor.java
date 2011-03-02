package com.neotechnology.cineasts.domain;

import org.springframework.data.graph.annotation.NodeEntity;

@NodeEntity
public class Actor {

	@Override
	public String toString() {
		return "I am an actor object";
	}
}
