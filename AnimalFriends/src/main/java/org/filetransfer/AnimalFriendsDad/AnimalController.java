package org.filetransfer.AnimalFriendsDad;

/*
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.filetransfer.AnimalFriendsDad.Entidades.Animal;
import org.filetransfer.AnimalFriendsDad.Repositorios.RepositorioAnimales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

///

@Controller
public class AnimalController {

	@Autowired
	private RepositorioAnimales animales;
	
	@PostConstruct
    public void init() {

    }
	
	@RequestMapping("/animales/nuevoAnimal")
	public void nuevoAnimal() {
		animales.save(new Animal(null, "Mono"));
		animales.save(new Animal(null, "Erizo"));
		
		//return "animal_nuevo"
	}
	
	@GetMapping("/")
	public Collection<Animal> getPosts() {
		return animales.findAll();
	}
	
}
*/