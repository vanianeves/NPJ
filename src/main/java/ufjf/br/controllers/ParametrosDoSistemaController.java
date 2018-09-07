package ufjf.br.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.ParametrosDoSistema;
import ufjf.br.service.ParametrosDoSistemaService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/parametrosDoSistema"})
public class ParametrosDoSistemaController {

    @Autowired
    private ParametrosDoSistemaService parametrosDoSistemaService;

    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/parametrosDoSistema/index");
        mv.addObject("parametros", parametrosDoSistemaService.findAll());


        return mv;
    }

    @GetMapping(value = "create")
    public ModelAndView create() {
        ParametrosDoSistema model = new ParametrosDoSistema();
        ModelAndView mv = new ModelAndView("/parametrosDoSistema/create");
        mv.addObject("parametro", model);

        return mv;
    }

    @PostMapping(value = "create")
    public ModelAndView create(@ModelAttribute @Valid ParametrosDoSistema parametrosDoSistema, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/parametrosDoSistema/create", "erros", result.getAllErrors());
            mv.addObject("parametro", parametrosDoSistema);

            return mv;
        }

        parametrosDoSistemaService.save(parametrosDoSistema);
        redirectAttributes.addFlashAttribute("messages", "Parâmetro criado com sucesso.");
        return new ModelAndView("redirect:/parametrosDoSistema/index");
    }

    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/parametrosDoSistema/update");
        mv.addObject("parametro", parametrosDoSistemaService.findOne(id));

        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView save(@PathVariable("id") Integer id, @ModelAttribute @Valid ParametrosDoSistema model, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/parametrosDoSistema/update");
            mv.addObject("parametro", model);

            return mv;
        }

        parametrosDoSistemaService.save(model);
        redirectAttributes.addFlashAttribute("messages", "Parâmetro editado com sucesso.");
        return new ModelAndView("redirect:/parametrosDoSistema/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        parametrosDoSistemaService.delete(id);
        redirectAttributes.addFlashAttribute("messages", "Parâmetro excluído com sucesso.");
        return new ModelAndView("redirect:/parametrosDoSistema/index");
    }
}
