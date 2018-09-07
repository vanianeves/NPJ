package ufjf.br.service;


import ufjf.br.models.Desdobramentos;
import ufjf.br.models.TipoSetor;
import ufjf.br.repository.DesdobramentosRepository;
import ufjf.br.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesdobramentosService{

    @Autowired
    private DesdobramentosRepository setorRepository;

    public List<Desdobramentos> findAll() {
        return setorRepository.findAll();
    }

    public Desdobramentos findOne(Integer id) {
        return setorRepository.findOne(id);
    }

    public Desdobramentos save(Desdobramentos typeofService) {
        return setorRepository.saveAndFlush(typeofService);
    }

    public void delete(Integer id) {
        setorRepository.delete(id);
    }
}





