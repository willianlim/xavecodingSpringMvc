package br.com.willcode.regesc.service;

import br.com.willcode.regesc.orm.Disciplina;
import br.com.willcode.regesc.orm.Professor;
import br.com.willcode.regesc.repository.DisciplinaRepository;
import br.com.willcode.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudDisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository,
                                 ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Disciplina");
            System.out.println("3 - Visualizar todoas disciplinas");
            System.out.println("4 - Deleta uma Disciplina");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.println("\nNome da disciplina: ");
        String nome = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

        System.out.println();
        System.out.println("\nSemestre: ");
        Integer semestre = scanner.nextInt(); // Ler a próxima string até achar um enter ou um espaço

        System.out.print("\nProfessor ID: ");
        Long professorID = scanner.nextLong();

        Optional<Professor> optional = professorRepository.findById(professorID);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo!\n");
        } else {
            System.out.println("Professor ID " + professorID + " inválido");
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("\nDigite o id da Disciplina a ser atualizada: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();

            System.out.println("\nDigite o nome da disciplina: ");
            String nome = scanner.next(); // Ler a próxima string até achar um enter ou um espaço

            System.out.println("\nSemestre: ");
            Integer semestre = scanner.nextInt(); // Ler a próxima string até achar um enter ou um espaço

            System.out.println("Professor ID: ");
            Long professorId = scanner.nextLong();

            Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);
            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado!\n");
            } else {
                System.out.println("Professor ID " + professorId + " inválido");
            }

        } else {
            System.out.println("O id do professor informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();

        for (Disciplina disciplina: disciplinas) {
            System.out.println(disciplina);
        }
        System.out.println();
    }

    private void deletar(Scanner scanner) {
        System.out.print("Id: ");
        Long id = scanner.nextLong();
        this.disciplinaRepository.deleteById(id); // Lançará uma exception se não achar o ID passado
        System.out.println("Disciplina Deletada!\n");
    }

}
