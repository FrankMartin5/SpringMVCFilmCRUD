package com.skilldistillery.film.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.data.DatabaseAccessorObject;
import com.skilldistillery.film.data.FilmDAO;

class FilmTests {
	private FilmDAO db;

	@BeforeEach
	void setUp() throws Exception{
		db = new DatabaseAccessorObject();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		db = null;
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	

}
