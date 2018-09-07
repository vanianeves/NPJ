package ufjf.br.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.TipoSetor;
import ufjf.br.service.SetorService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/tipoSetor"})
public class SetorController {
    @Autowired
    private SetorService setorService;

    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/tipoSetor/index");
        mv.addObject("setores", setorService.findAll());
        return mv;
    }

    @GetMapping(value = "/create")
    public ModelAndView create() {
        TipoSetor setor = new TipoSetor();
        ModelAndView mv = new ModelAndView("/tipoSetor/create");
        mv.addObject("tipoSetor", setor);
        return mv;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@ModelAttribute @Valid TipoSetor setor, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/tipoSetor/create");
            mv.addObject("tipoSetor", setor);
            return mv;
        }
        setorService.save(setor);
        redirectAttributes.addFlashAttribute("messages", "Setor criado com sucesso.");
        return new ModelAndView("redirect:/tipoSetor/index");

    }

    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/tipoSetor/update");
        mv.addObject("tipoSetor", setorService.findOne(id));

        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView save(@PathVariable("id") Integer id, @Valid TipoSetor setor, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/tipoSetor/update");
            mv.addObject("tipoSetor", setor);

            return mv;
        }

        setorService.save(setor);
        redirectAttributes.addFlashAttribute("messages", "\n\n Setor editado com sucesso.");
        return new ModelAndView("redirect:/tipoSetor/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        setorService.delete(id);
        redirectAttributes.addFlashAttribute("messages", "\n\n Setor excluído com sucesso.");
        return new ModelAndView("redirect:/tipoSetor/index");
    }
}
