package ufjf.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufjf.br.models.Colaborador;
import ufjf.br.models.Historico;
import ufjf.br.repository.ColaboradorRepository;
import ufjf.br.repository.HistoricoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoService {
    @Autowired
    private HistoricoRepository service;

    public List<Historico> findAll() {
        return service.findAll();
    }

    public Historico findOne(Integer id) {
        return service.findOne(id);
    }

    public Historico save(Historico entity) {
        return service.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        service.delete(id);
}

    public List<Historico> listarHistoricos(Integer id){
        List<Historico> historicos;
        List<Historico> historicosARetornar= new ArrayList<>();
        historicos= service.findAll();
        for (Historico historico: historicos) {
            if(historico.getIdAtendimento().equals(id)){
                historicosARetornar.add(historico);
            }
        }
        historicos=null;
        return  historicosARetornar;
    }

}
