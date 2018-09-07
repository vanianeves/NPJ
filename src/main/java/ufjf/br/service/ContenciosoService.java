//package ufjf.br.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ufjf.br.models.Colaborador;
//import ufjf.br.models.Contencioso;
//import ufjf.br.repository.ColaboradorRepository;
//import ufjf.br.repository.ContenciosoRepository;
//
//import java.util.List;
//
//@Service
//public class ContenciosoService {
//
//    @Autowired
//    private ContenciosoRepository contenciosoRepository;
//
//    public List<Contencioso> findAll() {
//        return contenciosoRepository.findAll();
//    }
//
//    public Contencioso findOne(Integer id) {
//        return contenciosoRepository.findOne(id);
//    }
//
//    public Contencioso save(Contencioso entity) {
//        return contenciosoRepository.saveAndFlush(entity);
//    }
//
//    public void delete(Integer id) {
//        contenciosoRepository.delete(id);
//}
//
//}
