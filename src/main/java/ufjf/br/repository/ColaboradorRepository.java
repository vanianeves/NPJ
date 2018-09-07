package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ufjf.br.models.Colaborador;
import ufjf.br.models.Estado;

import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

    @Query(value = "SELECT * FROM colaborador INNER JOIN tipo_colaborador  ON colaborador.tipo_colaborador_id = tipo_colaborador.id WHERE tipo_colaborador.id = 1", nativeQuery = true)
    List<Colaborador> findAlunos();

    @Query(value = "SELECT * FROM colaborador INNER JOIN tipo_colaborador  ON colaborador.tipo_colaborador_id = tipo_colaborador.id WHERE tipo_colaborador.id = 3", nativeQuery = true)
    List<Colaborador> findParfistas();





    Colaborador findColaboradorByNome(String nome);
    Colaborador findColaboradorByOab (String OAB);
    Colaborador findColaboradorByMatricula(String matricula);


}
