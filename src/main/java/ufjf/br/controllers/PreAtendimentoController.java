package ufjf.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ufjf.br.Utils.InvalidDateException;
import ufjf.br.Utils.PreAtendimentoPost;
import ufjf.br.models.PreAtendimento;
import ufjf.br.service.PreAtendimentoService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = {"/preAtendimento"})
public class PreAtendimentoController {


    @Autowired
    private PreAtendimentoService preAtendimentoService;

    @RequestMapping(value = {"index", "/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/preAtendimento/index");
        mv.addObject("preAtendimento", preAtendimentoService.findAllByOrderById());
        return mv;
    }

    @GetMapping(value = "create")
    public ModelAndView create() {
        PreAtendimentoPost preAtendimentoPost = new PreAtendimentoPost();
        ModelAndView mv = new ModelAndView("/preAtendimento/create");
        mv.addObject("preAtendimentoPost",preAtendimentoPost);
        mv.addObject("diasSemana", preAtendimentoService.diasSemana());

        return mv;
    }



    @PostMapping(value = "create")
    @Transactional
    public ModelAndView create( @Valid PreAtendimentoPost preAtendimentoPost,  BindingResult result, PreAtendimento model) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/preAtendimento/create", "erros", result.getAllErrors());
            mv.addObject("preAtendimento", model);
            return mv;
        }


        List<PreAtendimento> datas = preAtendimentoService.geraDatas(preAtendimentoPost);

        for (PreAtendimento data: datas
             ) {

            System.out.println();
            preAtendimentoService.save(data);
        }

        return new ModelAndView("redirect:/preAtendimento/index");

    }

    @GetMapping(value = "horario-extraordinario")
    public ModelAndView createExtraordinario() {
        PreAtendimentoPost preAtendimentoPost = new PreAtendimentoPost();
        ModelAndView mv = new ModelAndView("/preAtendimento/horario-extraordinario");
        mv.addObject("preAtendimentoPost",preAtendimentoPost);

        return mv;
    }

    @PostMapping(value = "horario-extraordinario")
    @Transactional
    public ModelAndView createExtraordinario( @Valid PreAtendimentoPost preAtendimentoPost,  BindingResult result, PreAtendimento model) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/preAtendimento/horario-extraordinario", "erros", result.getAllErrors());
            mv.addObject("preAtendimento", model);
            return mv;
        }



        List<PreAtendimento> datas = preAtendimentoService.gerarHorarioExtraordinario(preAtendimentoPost);


            for (PreAtendimento data : datas
                    ) {

                System.out.println(data.getData_horario());
               preAtendimentoService.save(data);
            }


        return new ModelAndView("redirect:/preAtendimento/index");

    }


    @GetMapping(value = {"/delete/{id}"})
    public ModelAndView delete(@PathVariable("id") Integer id){
        preAtendimentoService.delete(id);
        return new ModelAndView("redirect:/preAtendimento/index");
    }
}