package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOJdbcImpl implements FilmDAO{
	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private String user = "student";
	private String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description, release_year, rating, language_id FROM film WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDesc(filmResult.getString("description"));
				film.setRealeaseYear(filmResult.getInt("release_year"));
				film.setRating(filmResult.getString("rating"));
				film.setLangId(findLanguage(filmResult.getInt("language_id")));
				film.setActors(findActorsByFilmId(filmId));
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public String findLanguage(int langId) {
		String name = "";
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name FROM language WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, langId);
			ResultSet langResult = stmt.executeQuery();
			if (langResult.next()) {
				name = langResult.getString("name");
			}
			langResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name "
					+ " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rating "
					+ " FROM film JOIN actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				String langId = findLanguage(rs.getInt("language_id"));
				String rating = rs.getString("rating");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rating);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

//	}
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rating "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE film.title LIKE ?"
					+ " OR film.description LIKE ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				String langId = findLanguage(rs.getInt("language_id"));
				String rating = rs.getString("rating");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rating);
				films.add(film);
				film.setActors(findActorsByFilmId(filmId));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // Start Transaction

			String sql = "INSERT INTO film (title, language_id, rental_duration, rental_rate, replacement_cost)"
					+ "VALUES (?, 1, ?, ?, ?) ";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
//			stmt.setString(2, film.getLangId());
			stmt.setInt(2, film.getRentDur());
			stmt.setDouble(3, film.getRate());
			stmt.setDouble(4, film.getRepCost());
			int updateCount = stmt.executeUpdate();
//			System.out.println(updateCount + " film was created.");
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
				}
			}
			conn.commit();
			stmt.close();
			conn.close();
		} catch (
		SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
		}
		return film;
	}
	
	public boolean deleteFilm(Film film) {
		  Connection conn = null;
		  try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    String sql = "DELETE FROM film WHERE film_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, film.getId());
		    int updateCount = stmt.executeUpdate();
//		    sql = "DELETE FROM actor WHERE id = ?";
//		    stmt = conn.prepareStatement(sql);
//		    stmt.setInt(1, film.getId());
//		    updateCount = stmt.executeUpdate();
		    conn.commit();             // COMMIT TRANSACTION
		  }
		  catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); }
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    return false;
		  }
		  return true;
		}
	
	public boolean updateFilm(Film film) {
		  Connection conn = null;
		  try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    String sql = "UPDATE film SET title=?, language_id=?, rental_duration=?, rental_rate=?, replacement_cost=? "
		               + " WHERE id=?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, film.getTitle());
		    stmt.setString(2, film.getLangId());
		    stmt.setInt(3, film.getRentDur());
		    stmt.setDouble(4, film.getRate());
		    stmt.setDouble(5, film.getRepCost());
		    int updateCount = stmt.executeUpdate();
		    if (updateCount == 1) {
		      // Replace actor's film list
		      sql = "DELETE FROM film_actor WHERE actor_id = ?";
		      stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, film.getId());
		      updateCount = stmt.executeUpdate();
		      sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
		      stmt = conn.prepareStatement(sql);
		      for (Actor actor : film.getActors()) {
				stmt.setInt(1, actor.getId());
				stmt.setInt(2, actor.getId());
				updateCount = stmt.executeUpdate();
		      }
		      conn.commit();           // COMMIT TRANSACTION
		    }
		  } catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); } // ROLLBACK TRANSACTION ON ERROR
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    return false;
		  }
		  return true;
		}

}
