package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufjf.br.models.PreAtendimento;

import java.util.List;

public interface PreAtendimentoRepository extends JpaRepository<PreAtendimento, Integer> {

   List<PreAtendimento> findAllByOrderById();

   @Query(value = "SELECT * FROM pre_atendimento Inner Join agendamento_usuario On pre_atendimento.id != agendamento_usuario.pre_atendimento_id Order by pre_atendimento.id ASC", nativeQuery = true)
   List<PreAtendimento> findAllScheduled();

   @Override
   PreAtendimento findOne(Integer integer);
}
