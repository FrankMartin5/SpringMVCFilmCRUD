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
	
	@RequestMapping(path ="home.do")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping(path = "GetFilmById.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView getFilmById(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(filmId);
		mv.addObject("film", film);
		mv.setViewName("filmIdResult.jsp");
		return mv;
	}
	
	@RequestMapping(path = "createFilm.do", params = "createFilm", method = RequestMethod.GET)
	public ModelAndView createFilm(Film filmData) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.createFilm(filmData);
		mv.addObject("film", film);
		mv.setViewName("createFilmResult.jsp");
		return mv;
	}
	
	
	
	
	
	
	
	

}
