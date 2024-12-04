package rin.copmani.RikiAndMortiAPIUrlDownloader.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rin.copmani.RikiAndMortiAPIUrlDownloader.configuration.UrlConfiguration;
import rin.copmani.RikiAndMortiAPIUrlDownloader.models.Characters;
import rin.copmani.RikiAndMortiAPIUrlDownloader.models.Result;
import rin.copmani.RikiAndMortiAPIUrlDownloader.service.ServiceApi;

@Log
@Controller
@AllArgsConstructor
public class ControllerApi {
    private final UrlConfiguration urlConfiguration;
    private ServiceApi serviceApi;

    //контроллер основной страницы
    @GetMapping(value = "/")
    public String getCharacters(Model model){
        Characters allCharacters = serviceApi.getAllCharacters("");
        log.info("GET '/' info: " + allCharacters.getInfo());
        log.info("GET '/' results: " + allCharacters.getResults());
        model.addAttribute("results",allCharacters.getResults());
        return "template.html";
    }
    //контроллер следующей подгружаемой страницы
    @GetMapping("/next")
    public  String nextgetCharacters(Model model){
        Characters allCharacters = serviceApi.getAllCharacters("next");
        log.info("GET '/next' info: " + allCharacters.getInfo());
        log.info("GET '/next' results: " + allCharacters.getResults());
        model.addAttribute("results",allCharacters.getResults());
        return "template.html";
    }
    //контроллер предыдущей подгружаемой страницы
    @GetMapping("/prev")
    public  String prevgetCharacters(Model model){
        Characters allCharacters = serviceApi.getAllCharacters("prev");
        log.info("GET '/prev' info: " + allCharacters.getInfo());
        log.info("GET '/prev' results: " + allCharacters.getResults());
        model.addAttribute("results",allCharacters.getResults());
        return "template.html";
    }
    @GetMapping("/{id}")
    public String getCharacterById(@PathVariable("id") Integer id, Model model){
        Result result = serviceApi.getResultById(id);
        if(result ==null) return "redirect:/";
        log.info("GET '/{id}' Reqvest by id craracter where id = " + id);
        log.info("Result = "+ result);
        model.addAttribute("result",result);
        return "templateOneCharacter";

    }
}
