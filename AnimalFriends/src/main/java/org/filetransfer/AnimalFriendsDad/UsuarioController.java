package org.filetransfer.AnimalFriendsDad;

import org.filetransfer.AnimalFriendsDad.Repositorios.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController {
	
	/*@Autowired
	private RepositorioUsuarios usuarios;
	/*
	@GetMapping("/login")
	public String login() {
		return "login";
	}


	@GetMapping("/loginerror")
	public String loginerror(Model model) {
		model.addAttribute("mensaje", "Error al iniciar sesión");
		return "fallo";
	}
	
	
	/*@GetMapping("/sesionIniciada")
	public String sesionIniciada(Model model, HttpSession sesion) {
		Usuarios usuario = usuarios.findByNombre(SecurityContextHolder.getContext().getAuthentication().getName());
		sesion.setAttribute("idUsuario", usuario.getId());
		model.addAttribute("nombre", usuario.getNombre());
		return "sesion_iniciada";
	}*//*

	@GetMapping("/registroUsuario")
	public String registroUsuario() {
		return "registro_usuario";
	}

	// Registro de un nuevo usuario
	@PostMapping("/usuarioNuevo")
	public String nuevoUsuario(Model model, @RequestParam String nombre, @RequestParam String contrasenia) {
		// Si no existe un usuario con el mismo nombre, se registra
		if (usuarios.findByNombre(nombre) == null) {
			Usuarios usuario = new Usuarios(nombre, contrasenia);
			
			usuarios.save(usuario);
			model.addAttribute("nombre", usuario.getNombre());
			return "usuario_registrado";
		} else {
			model.addAttribute("mensaje", "Error al registrar usuario");
			return "fallo";
		}
	}
	
	@PostConstruct
	public void init() {
		usuarios.save(new Usuarios("Estefania","771992"));
		usuarios.save(new Usuarios("Pepito","451992"));
	}
	
	@GetMapping("/")
	public Page<Usuarios> getUsuarios(@RequestParam(required = false)String user,Pageable page){
		if(user != null) {
			return usuarios.findByUser(user,page);
		}else {
			return usuarios.findAll(page);
		}
	}*/

}
