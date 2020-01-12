package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilm(Film filmData) {
		ModelAndView mv = new ModelAndView();
		Film film = dao.createFilm(filmData);
		mv.addObject("film", film);
		mv.setViewName("createFilmResult.jsp");
		return mv;
	}
	
	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(@RequestParam("id") int id) {
		Film film = dao.findFilmById(id);
		boolean deleteFilm = dao.deleteFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("Delete",deleteFilm);
		mv.setViewName("deleteFilmResult.jsp");
		return mv;
	}
//	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
//	  public String deleteFilm(Film film, RedirectAttributes redir) {
//	    dao.createFilm(film);
//	    redir.addFlashAttribute("film", film);
//	    return "redirect:createFilm.do";
//	  }
//	@RequestMapping(path="createFilm.do", method=RequestMethod.GET)
//	public ModelAndView created() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("deleteFilmResult.jsp");
//		return mv;
//	}
//	
	
	
	
	
	
	
	

}
