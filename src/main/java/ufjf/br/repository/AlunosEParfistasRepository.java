package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufjf.br.models.AgendamentoUsuario;
import ufjf.br.models.AlunosParfistasPorAtendimento;

import java.util.List;

public interface AlunosEParfistasRepository extends JpaRepository<AlunosParfistasPorAtendimento, Integer>{

    List<AlunosParfistasPorAtendimento> findAllByOrderById();


}
