package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufjf.br.models.AtendimentoInicial;

@Repository
public interface AtendimentoInicialRepository extends JpaRepository<AtendimentoInicial, Integer>{

}
