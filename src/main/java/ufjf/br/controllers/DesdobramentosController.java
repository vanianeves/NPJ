package ufjf.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.Desdobramentos;
import ufjf.br.models.TipoSetor;
import ufjf.br.service.DesdobramentosService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/desdobramentos"})
public class DesdobramentosController{


@Autowired
private DesdobramentosService desdobramentosService;


@RequestMapping(value = {"/index"})
    public ModelAndView index (){

    ModelAndView mv = new ModelAndView("/desdobramentos/index");
    mv.addObject("desdobramentos",desdobramentosService.findAll());
 return mv;
}

@GetMapping(value={"/create"})
 public ModelAndView create(){

 ModelAndView mv = new ModelAndView("/desdobramentos/create");
    Desdobramentos desdobramento = new Desdobramentos();
   mv.addObject("desdobramento",desdobramento);
   return mv;
}

    @PostMapping(value = "/create")
    public ModelAndView create(@ModelAttribute @Valid Desdobramentos desdobramento, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/desdobramentos/create");
            mv.addObject("desdobramento", desdobramento);
            return mv;
        }
        desdobramentosService.save(desdobramento);
        redirectAttributes.addFlashAttribute("messages", "desdobramento criado com sucesso.");
        return new ModelAndView("redirect:/desdobramentos/index");

    }


    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/desdobramentos/update");
        mv.addObject("desdobramento", desdobramentosService.findOne(id));

        return mv;
    }




    @PostMapping("/update/{id}")
    public ModelAndView save(@PathVariable("id") Integer id, @Valid Desdobramentos desdobramento, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/desdobramentos/update");
            mv.addObject("desdobramento", desdobramento);

            return mv;
        }

        desdobramentosService.save(desdobramento);
        redirectAttributes.addFlashAttribute("messages", "\n\n desdobramento editado com sucesso.");
        return new ModelAndView("redirect:/desdobramentos/index");
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        desdobramentosService.delete(id);
        redirectAttributes.addFlashAttribute("messages", "\n\n dedobramento excluído com sucesso.");
        return new ModelAndView("redirect:/tipoSetor/index");
    }
    







}
