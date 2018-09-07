package ufjf.br.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.AtendimentoInicial;
import ufjf.br.models.Historico;
import ufjf.br.service.AtendimentoInicialService;
import ufjf.br.service.HistoricoService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"/historico"})
public class HistoricoController {
    private Integer idAtendimentoTemporaria;


    @Autowired
    private HistoricoService service;

    @Autowired
    AtendimentoInicialService atendimentoService;


    @RequestMapping(value = {"index/{id}"})
    public ModelAndView index(@PathVariable ("id") Integer id) {
        List<Historico> historicos = service.listarHistoricos(id);

        ModelAndView mv = new ModelAndView("/historico/index");
        mv.addObject("historicos", historicos);
        return mv;
    }


    /*@GetMapping(value = {"/create"})
    ModelAndView create() {
        ModelAndView mv = new ModelAndView("/historico/create");
        Historico historico = new Historico();
        Date data = new Date();
        data.getTime();
        String ajusta = data.toString();
        String diaSemana = ajusta.substring(0, 3);
        String dia = ajusta.substring(8, 10);
        String mes = ajusta.substring(4, 7);
        String ano = ajusta.substring(24, 28);
        ajusta = diaSemana + " " + dia + "/" + mes + "/" + ano;
        historico.setDtHistorico(ajusta);
        mv.addObject("historico", historico);
        return mv;
    }*/

    @GetMapping(value = "create/{id}")
    ModelAndView create(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/historico/create");
        Historico historico = new Historico();
        idAtendimentoTemporaria = id;
        Date data = new Date();
        data.getTime();
        String ajusta = data.toString();
        String diaSemana = ajusta.substring(0, 3);
        String dia = ajusta.substring(8, 10);
        String mes = ajusta.substring(4, 7);
        String ano = ajusta.substring(24, 28);
        ajusta = diaSemana + " " + dia + "/" + mes + "/" + ano;
        historico.setDtHistorico(ajusta);
        mv.addObject("historico", historico);
        return mv;
    }





    @PostMapping(value = "create")
    ModelAndView create(
            @ModelAttribute @Valid Historico historico,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        ModelAndView mv = new ModelAndView("/historico/create", "erros", result.getAllErrors());
        if (result.hasErrors()) {
            mv.addObject("historico", historico);

            return mv;
        }
        historico.setIdAtendimento(idAtendimentoTemporaria);
        service.save(historico);

        redirectAttributes.addFlashAttribute("messages", "historico adicionado com sucesso.");
        return new ModelAndView("redirect:/atendimento/index");
    }


    @GetMapping(value = "update/{id}")
    ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("historico/update");
        mv.addObject("historico", service.findOne(id));
        return mv;
    }


    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView save(@PathVariable("id") Integer id, @ModelAttribute @Valid Historico model, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/historico/update");
            mv.addObject("historico", model);
            return mv;
        }

        service.save(model);
        redirectAttributes.addFlashAttribute("messages", "historico editado com sucesso.");
        String url = "redirect:/historico/index/" + model.getIdAtendimento();
        return new ModelAndView(url);

    }

    @GetMapping(value = "/delete/{id}")
    ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Integer idAtendimento = service.findOne(id).getIdAtendimento();
        service.delete(id);
        redirectAttributes.addFlashAttribute("messages", "historico deletado com sucesso.");
        String url = "redirect:/historico/index/" + idAtendimento;
        return new ModelAndView(url);

    }




    //get e set's


    public Integer getIdAtendimentoTemporaria() {
        return idAtendimentoTemporaria;
    }

    public void setIdAtendimentoTemporaria(Integer idAtendimentoTemporaria) {
        this.idAtendimentoTemporaria = idAtendimentoTemporaria;
    }
}




