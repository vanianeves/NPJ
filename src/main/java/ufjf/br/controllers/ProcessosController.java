package ufjf.br.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.Historico;
import ufjf.br.models.Processos;
import ufjf.br.service.ProcessosService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"/processos"})
public class ProcessosController {


    private Integer idAtendimentoTemporaria;

    @Autowired
    private ProcessosService service;



    @RequestMapping(value = {"index/{id}"})
    public ModelAndView index(@PathVariable ("id") Integer id) {
        List<Processos> processos = service.listarProcessos(id);
        ModelAndView mv = new ModelAndView("/processos/index");
        mv.addObject("processos", processos);
        return mv;
    }



    @GetMapping(value = "create/{id}")
    ModelAndView create(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/processos/create");
        Processos processo = new Processos();
        idAtendimentoTemporaria = id;
        Date data = new Date();
        data.getTime();
        String ajusta = data.toString();
        String diaSemana = ajusta.substring(0, 3);
        String dia = ajusta.substring(8, 10);
        String mes = ajusta.substring(4, 7);
        String ano = ajusta.substring(24, 28);
        ajusta = diaSemana + " " + dia + "/" + mes + "/" + ano;
        processo.setData(ajusta);
        mv.addObject("processo",processo);
        return mv;
    }

    @PostMapping(value = "create")
    ModelAndView create(
            @ModelAttribute @Valid Processos processo,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        ModelAndView mv = new ModelAndView("/processos/create", "erros", result.getAllErrors());
        if (result.hasErrors()) {
            mv.addObject("processo", processo);
            return mv;
        }
       processo.setIdAtendimento(idAtendimentoTemporaria);
        service.save(processo);

        redirectAttributes.addFlashAttribute("messages", "Processo adicionado com sucesso.");
        return new ModelAndView("redirect:/atendimento/index");
    }


    @GetMapping(value = "update/{id}")
    ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("processos/update");
        mv.addObject("processo", service.findOne(id));
        return mv;
    }


    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView save(@PathVariable("id") Integer id,@ModelAttribute @Valid Processos model, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/processos/update");
            mv.addObject("processo", model);
            return mv;
        }

        service.save(model);
        redirectAttributes.addFlashAttribute("messages", "Processo editado com sucesso.");
        String url = "redirect:/processos/index/" + model.getIdAtendimento();
        return new ModelAndView(url);

    }

    @GetMapping(value = "/delete/{id}")
    ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Integer idAtendimento = service.findOne(id).getIdAtendimento();
        service.delete(id);
        redirectAttributes.addFlashAttribute("messages", "Processos deletado com sucesso.");
        String url = "redirect:/processos/index/" + idAtendimento;
        return new ModelAndView(url);

    }



}




