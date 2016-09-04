package org.abc.openlaw.controller;

import org.abc.openlaw.controller.commands.LawCommand;
import org.abc.openlaw.domain.Law;
import org.abc.openlaw.domain.LawVersion;
import org.abc.openlaw.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by scamisay on 05/02/16.
 */
@Controller
@RequestMapping(value = "/law")
public class LawController {

    @Autowired
    private LawService lawService;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam String id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("law/edit");
        LawCommand law = new LawCommand(lawService.findLawById(id));
        mav.addObject("law", law);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("law/create");
        mav.addObject("law", new LawCommand());
        return mav;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(LawCommand lawCommand){
        Law aLaw = lawCommand.build();
        lawService.saveLaw(aLaw);
        return "redirect:/law/create";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("law/list");
        mav.addObject("list", lawService.findAllLaws());
        return mav;
    }
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editPost(
            @RequestParam("lawId") String lawId,
            @RequestParam("articleNumber") Integer articleNumber,
            @RequestParam("articleValue") String articleValue){
        Law law = lawService.findLawById(lawId);
        LawVersion newLawVersion = lawService.editArticle(law, articleNumber, articleValue);
        /*String a = version.getArticles().get(articleNumber-1).getGeneral();
        a = a.replaceAll("\r","");
        int beginning = 30;
        int offset = 45;

        String b = a.substring(beginning,offset)+"|"+articleValue.substring(beginning,offset);
        a.substring(beginning,offset).equals(articleValue.substring(beginning,offset));
        a.equals(articleValue)*/
    }

/*
    LawVersion aLaw = new LawVersion("ley 1");
    aLaw.addArticle(new Article("Articulo 1").addItem(new Item("Inciso 1")).addItem(new Item("Inciso 2")))
            .addArticle(new Article("Articulo 2"));
            */
}
