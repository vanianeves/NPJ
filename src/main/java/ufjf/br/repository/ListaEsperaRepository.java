package ufjf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufjf.br.models.ListaEspera;

import java.beans.Transient;
import java.util.List;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera,Integer>{
    public List<ListaEspera> findAllByOrderById();


    public ListaEspera findFirstByOrderByIdAsc();
}
