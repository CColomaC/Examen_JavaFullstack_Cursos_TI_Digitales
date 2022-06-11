package cl.christian.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.christian.cursos.model.Administrador;
import cl.christian.cursos.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	AdministradorRepository administradorRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public Administrador crearAdmin(Administrador admin) {
		String passCodificado = encoder.encode(admin.getPassword());
		admin.setPassword(passCodificado);
		return administradorRepository.save(admin);
	}
	
	public long contarAdmin() {
		return administradorRepository.count();
	}
}
