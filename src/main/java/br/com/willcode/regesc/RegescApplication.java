package br.com.willcode.regesc;

import br.com.willcode.regesc.orm.Professor;
import br.com.willcode.regesc.repository.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {

	private ProfessorRepository repository;

	public RegescApplication(ProfessorRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Professor professor = new Professor("Willian", "xyz");

		System.out.println("Professor ANTES do save (persistência com o BD)");
		System.out.println(professor);

		this.repository.save(professor);

		System.out.println("Professor DEPOIS do save (persistência com o BD)");
		System.out.println(professor);
	}
}
