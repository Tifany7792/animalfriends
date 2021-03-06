package org.filetransfer.AnimalFriendsDad;

import org.filetransfer.AnimalFriendsDad.Repositorios.RepositorioAnimales;
import org.filetransfer.AnimalFriendsDad.Repositorios.RepositorioUsuarios;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.filetransfer.AnimalFriendsDad.Entidades.Animal;
import org.filetransfer.AnimalFriendsDad.Entidades.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnimalController {
	@Autowired
	private RepositorioAnimales animales;


	@Autowired
	private RepositorioUsuarios repusu;


	@PostConstruct
	public void init() {
		animales.save(new Animal("Mono",
				"mono pequeño, en peligro de extinción, que come bichos y pequeños mamiferos"));
		animales.save(
				new Animal("erizo", "mamifero de la familia de los topos, con el cuerpo cubierto de puas"));

	}

	@GetMapping("/animales/new")
	public String createAnimal(Model model, HttpServletRequest request) {

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		if (token != null) {
			model.addAttribute("token", token.getToken());
		}
		return "new_animal";
	}
	
	

	@RequestMapping("/animales/{id}/añadir")
	public String tenerMascota(Model model, HttpServletRequest request, @PathVariable long id) {
		
		String nombre = request.getUserPrincipal().getName();
		Usuarios userIniciado = repusu.findByNombre(nombre).get();
		
		if (nombre != "") {
			Animal ani = animales.getById(id);
			userIniciado.addMascotas(ani);
			animales.save(ani);
		}
		return "redirect:/usuario";
	}
	
	@RequestMapping("/animales/{id}/delete")
	public String borrarMascotas(Model model, @PathVariable long id) {
		
		animales.deleteById(id);
		
		return "deleted_animal";
	}
	
	@PostMapping("/animales/new/created")
	public String newAnimal(Animal a, HttpServletRequest request) {
		
		animales.save(a);
		String nombre = request.getUserPrincipal().getName();
		Usuarios u = repusu.findByNombre(nombre).get();
		if (permiso(request)) {
			u.addMascotas(a);
		}
		return "saved_animal";
	}

	@GetMapping("/animales")
	public String verAnimales(Model model, HttpServletRequest request) {
		model.addAttribute("animales", animales.findAll());
		model.addAttribute("permiso", permiso(request));
		return "list_animals";
	}

	private boolean permiso(HttpServletRequest request) {
		if (request.getUserPrincipal() == null) {
			return false;
		}

		return request.isUserInRole("ADMIN");
	}

	@GetMapping("/animales/{id}")
	public String showAnimal(Model model, HttpServletRequest request, @PathVariable long id) {
		Animal ani = animales.getById(id);

		model.addAttribute("animal", ani);
		model.addAttribute("permiso", permiso(request));

		return "show_animal";
	}

	@GetMapping("/animales/{id}/delete")
	public String deleteAnimal(Model model, @PathVariable long id) {
		animales.deleteById(id);

		return "deleted_animal";
	}	
	
	@RequestMapping("/animales/delete")
	public String borrarMascotas(Model model, HttpServletRequest request) {
		
		String nombre = request.getUserPrincipal().getName();
		Usuarios u = repusu.findByNombre(nombre).get();
		
		if (nombre != "") {
			u.deleteMascotas();
			animales.deleteAll();
		}
		init();
		return "redirect:/usuario";
	}
	

}