package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufjf.br.models.Cidade;
import ufjf.br.models.Processos;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<Processos, Integer> {
}
