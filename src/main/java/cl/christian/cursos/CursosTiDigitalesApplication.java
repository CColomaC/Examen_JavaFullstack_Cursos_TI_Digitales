package cl.christian.cursos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.christian.cursos.model.Administrador;
import cl.christian.cursos.model.Curso;
import cl.christian.cursos.model.Estudiante;
import cl.christian.cursos.repository.CursoRepository;
import cl.christian.cursos.service.AdministradorService;
import cl.christian.cursos.service.UsuarioService;

@SpringBootApplication
public class CursosTiDigitalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosTiDigitalesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner datosIniciales(AdministradorService aService, UsuarioService uService, CursoRepository cRepo) {
		return args -> {
			if(aService.contarAdmin() == 0) {
				Administrador admin = Administrador.builder()
													.username("admin")
													.password("1234")
													.build();
				aService.crearAdmin(admin);
														
			}
			
			if(uService.contarUsuarios() == 0) {
				Estudiante estudiante = Estudiante.builder()
											.nombre1("Juan")
											.nombre2("Manuel")
											.apellidoPaterno("Saavedra")
											.apellidoMaterno("Vega")
											.direccion("Acacia 22")
											.region("Araucania")
											.telefono("969617323")
											.email("mail@pagina.com")
											.rut("11222333-4")
											.password("1234")
											.region("Araucania")
											.comuna("Temuco")
											.build()
									;
				uService.crearUsuario(estudiante);
			}
			if(cRepo.count() == 0) {
				Curso cursoJava = Curso.builder()
										.nombre("Fundamentos de Programacion en Java")
										.fechaInicio(LocalDate.of(2022, 6, 13))
										.fechaFin(LocalDate.of(2023, 01, 27))
										.cupos(30)
										.descripcion("Fundamentos básicos para crear aplicaciones con interfaces de usuario.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/java.jpg")))
										.build();
				Curso cursoSpring = Curso.builder()
										.nombre("Desarrollo Web con Spring Framework")
										.fechaInicio(LocalDate.of(2023, 3, 3))
										.fechaFin(LocalDate.of(2023, 12, 20))
										.cupos(30)
										.descripcion("Para desarrollar aplicaciones Java de forma agil.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/spring.jpg")))
										.build();
				Curso cursoOracle = Curso.builder()
										.nombre("Bases de datos con Oracle")
										.fechaInicio(LocalDate.of(2023, 3, 3))
										.fechaFin(LocalDate.of(2023, 12, 20))
										.cupos(30)
										.descripcion("Creación, y mantención de bases de datos relacionales utilizando el lenguaje SQL.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/oracle.jpg")))
										.build();
				cRepo.save(cursoJava);
				cRepo.save(cursoSpring);
				cRepo.saveAndFlush(cursoOracle);
			}
		};
	}
	
	

}