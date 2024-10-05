package com.camiloduran.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorPeliculas {
	private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();
	
	public ControladorPeliculas() {
		listaPeliculas.put("Winnie the Pooh", "Don Hall");	
		listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
		listaPeliculas.put("Tarzán", "Kevin Lima");		
		listaPeliculas.put("Mulán", "Barry Cook");
		listaPeliculas.put("Oliver", "Kevin Lima");	
		listaPeliculas.put("Big Hero 1", "Don Hall");
		listaPeliculas.put("Big Hero 2", "Don Hall");
		listaPeliculas.put("Big Hero 3", "Don Hall");
	}

	@GetMapping("/peliculas")
	public String obtenerTodasLasPeliculas() {
	    String peliculas = "Lista de películas: ";
	    for (String pelicula : listaPeliculas.keySet()) {
	        peliculas += pelicula + " ";
	    }
	    return peliculas;
	}
	
	@GetMapping("/peliculas/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable("nombre") String nombre) {
        String director = listaPeliculas.get(nombre);
        if (director != null) {
            return nombre + " - " + director;
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
    }
	
	
	@GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable("nombre") String nombre) {
		List<String> peliculasDelDirector = new ArrayList<>();
		
		for (String pelicula : listaPeliculas.keySet()) {
	        if (listaPeliculas.get(pelicula).equalsIgnoreCase(nombre)) {
	            peliculasDelDirector.add(pelicula);
	        }
	    }
		
		if (peliculasDelDirector.isEmpty()) {
	        return "No contamos con películas con ese director en nuestra lista.";
	    } else {
	        return "Películas dirigidas por " + nombre + ": " + String.join(", ", peliculasDelDirector);
	    }
    }
}
