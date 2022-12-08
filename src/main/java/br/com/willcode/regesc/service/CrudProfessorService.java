package br.com.willcode.regesc.service;

import br.com.willcode.regesc.orm.Professor;
import br.com.willcode.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
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

        System.out.println("Digite o prontuário do professor: ");
        String prontuario = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no banco!!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()) {
            System.out.println("Digite o nome do professor: ");
            String nome = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

            System.out.println("Digite o prontuário do professor: ");
            String prontuario = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            professorRepository.save(professor); // atualiza (persiste) o objecto/registro/tupla no BD
            System.out.println("Professor atualizado com sucesso !!!\n");

        } else {
            System.out.println("O id do professor informado: " + id + " é inválido\n");
        }
    }

    private void atualizarSerFindById(Scanner scanner) {
        System.out.println("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        System.out.println("Digite o nome do professor: ");
        String nome = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

        System.out.println("Digite o prontuário do professor: ");
        String prontuario = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

        Professor professor = new Professor();
        professor.setId(id);
        professor.setNome(nome);
        professor.setProntuario(prontuario);

        professorRepository.save(professor);
        System.out.println("Professor atualizado com sucesso !!!\n");

    }

}
