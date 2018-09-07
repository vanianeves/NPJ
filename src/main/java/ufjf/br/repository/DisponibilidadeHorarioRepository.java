package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ufjf.br.models.Colaborador;
import ufjf.br.models.DisponibilidadeHorario;
import ufjf.br.models.Semana;
import ufjf.br.models.Usuario;

import java.time.LocalTime;
import java.util.List;
@Repository
public interface DisponibilidadeHorarioRepository extends JpaRepository<DisponibilidadeHorario, Integer> {
    boolean existsDisponibilidadeHorariosByUsuarioAndDiaAndHorarioinicioBetween(Colaborador usuario, Semana dia, LocalTime inicio, LocalTime fim);
    boolean existsDisponibilidadeHorariosByUsuarioAndDiaAndHorariofimBetween(Colaborador usuario, Semana dia, LocalTime inicio, LocalTime fim);
    @Query(value = "SELECT sum(disp.cargahoraria) FROM DisponibilidadeHorario disp where disp.usuario.id = ?1")
    LocalTime totalizarCargaHoraria(int id);
    @Query(value = "SELECT disp from DisponibilidadeHorario disp order by disp.dia,disp.horarioinicio,disp.horariofim")
    List<DisponibilidadeHorario> findAllByOrderByDiaAndHorarioInicio();

}
