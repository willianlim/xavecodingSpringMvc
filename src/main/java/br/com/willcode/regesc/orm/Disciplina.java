package br.com.willcode.regesc.orm;

import jakarta.persistence.*;

@Table(name = "disciplinas")
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Deprecated
    public Disciplina() {
    }

    public Disciplina(String nome, Integer semestre) {
        this.nome = nome;
        this.semestre = semestre;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
}
