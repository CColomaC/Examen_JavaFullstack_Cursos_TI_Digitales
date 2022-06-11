package cl.christian.cursos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.christian.cursos.model.Administrador;
import cl.christian.cursos.model.Estudiante;
import cl.christian.cursos.repository.AdministradorRepository;
import cl.christian.cursos.repository.EstudianteRepository;
import cl.christian.cursos.security.UserSeguridad;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	AdministradorRepository administradorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Estudiante> usuarioOpt = estudianteRepository.findByRut(username);
		if(usuarioOpt.isPresent()) {
			return new UserSeguridad(usuarioOpt.get(), null);
		}else {
			Optional<Administrador> adminOpt = administradorRepository.findByUsername(username);
			if(adminOpt.isPresent()) {
				return new UserSeguridad(null, adminOpt.get());
			}
		}
		throw new UsernameNotFoundException("Usuario no encontrado");
	}

}
