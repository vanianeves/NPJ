package ufjf.br.service;


import ufjf.br.models.TipoSetor;
import ufjf.br.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public List<TipoSetor> findAll() {
        return setorRepository.findAll();
    }

    public TipoSetor findOne(Integer id) {
        return setorRepository.findOne(id);
    }

    public TipoSetor save(TipoSetor typeofService) {
        return setorRepository.saveAndFlush(typeofService);
    }

    public void delete(Integer id) {
        setorRepository.delete(id);
    }
}
