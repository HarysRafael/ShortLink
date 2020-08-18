package com.shortlink.controller;

import com.shortlink.controller.dto.ShortLinkDTO;
import com.shortlink.controller.dto.ShortLinkResponse;
import com.shortlink.model.ShortLink;
import com.shortlink.service.ShortLinkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    private final ModelMapper modelMaper;

    @Autowired
    public ShortLinkController(ShortLinkService shortLinkService, ModelMapper modelMapper) {
        this.shortLinkService = shortLinkService;
        this.modelMaper = modelMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortLinkResponse cadastrar(@RequestBody @Valid ShortLinkDTO linkDTO, HttpServletRequest httpServletRequest) {
        ShortLink shortLink = shortLinkService.verificarCadastro(linkDTO.getUrl());
        String shortUrl= httpServletRequest.getRequestURL().toString()+shortLink.getHash();
        ShortLinkResponse modelMap = modelMaper.map(shortLink, ShortLinkResponse.class);
        modelMap.setShortlink(shortUrl);
        return modelMap;
    }

    @GetMapping("/{hash}")
    public RedirectView buscarUrl(@PathVariable String hash) {
        return shortLinkService.redirecionarUrlOriginal(hash);
    }
}
