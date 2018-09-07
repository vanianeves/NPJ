package ufjf.br.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufjf.br.models.Desdobramentos;

@Repository
public interface DesdobramentosRepository extends JpaRepository<Desdobramentos, Integer> {
}
