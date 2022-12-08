package br.com.willcode.regesc.repository;

import br.com.willcode.regesc.orm.Disciplina;
import org.springframework.data.repository.CrudRepository;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
}
