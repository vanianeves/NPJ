package ufjf.br.service;

import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ufjf.br.models.AtendimentoInicial;
import ufjf.br.repository.AtendimentoInicialRepository;
import java.util.List;


@Service
public class AtendimentoInicialService {

    @Autowired
    private AtendimentoInicialRepository atendimentoInicialRepository;

    public List<AtendimentoInicial> findAll() {
        return atendimentoInicialRepository.findAll();
    }


    public AtendimentoInicial findOne(Integer id) {
        return atendimentoInicialRepository.findOne(id);
    }

    public AtendimentoInicial save(AtendimentoInicial atendimentoInicial) {
        return atendimentoInicialRepository.saveAndFlush(atendimentoInicial);
    }

    public void delete(Integer id) {
        atendimentoInicialRepository.delete(id);
    }

    public void insere(int id){


    }









}
