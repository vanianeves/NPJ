package ufjf.br.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufjf.br.models.AreaAtuacao;
import ufjf.br.models.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
}
