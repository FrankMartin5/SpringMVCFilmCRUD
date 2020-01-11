package com.skilldistillery.film.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController{
	
	@Autowired
	private FilmDAO dao;

	public void setDao(FilmDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(path = "GetFilmById.do", params = "Id", method = RequestMethod.GET)
	public ModelAndView getFilmById(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(filmId);
		mv.addObject("film", film);
		mv.setViewName("home");
		return mv;
	}
	
	
	

}
