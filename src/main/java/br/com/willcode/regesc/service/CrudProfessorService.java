package br.com.willcode.regesc.service;

import br.com.willcode.regesc.orm.Professor;
import br.com.willcode.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudProfessorService {

    private ProfessorRepository professorRepository; // Dependência da classe CrudProfessorService

    /**
     * O Spring automaticamente cria um objecto com a inteface 'ProfessorRespository'
     * e o injeta para nós no construtor da classe atual ==> Injeção de dependência
     *
     * @param professorRepository
     */
    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo professor");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.println("Digite o nome do professor: ");
        String nome = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

        System.out.println("Digite o protuário do professor: ");
        String prontuario = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no banco!!\n");
    }

}
