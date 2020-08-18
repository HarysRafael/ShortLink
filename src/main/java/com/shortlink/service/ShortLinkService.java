package com.shortlink.service;

import com.shortlink.model.ShortLink;
import com.shortlink.repository.ShortLinkRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import java.net.URL;
import java.util.Optional;

@Service
public class ShortLinkService {
    private final ShortLinkRepository shortLinkRepository;

    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository){
        this.shortLinkRepository = shortLinkRepository;
    }

    public ShortLink cadastro(URL url) {
        ShortLink link = new ShortLink();
        link.setUrl(url);
        gerarHash(link);
        link.setAcessos(1L);
        return shortLinkRepository.save(link);
    }
    public ShortLink verificarCadastro(URL url) {
        Optional<ShortLink> urlEncontrada = shortLinkRepository.findByUrl(url);
        if (urlEncontrada.isPresent()) {
            return atualizarAcesso(urlEncontrada.get());
        } else {
            return cadastro(url);
        }
    }

    public ShortLink gerarHash(ShortLink link) {
        String hash = RandomStringUtils.randomAlphanumeric(5);
        link.setHash(hash);
        return link;

    }

    public ShortLink atualizarAcesso(ShortLink link) {
        link.atualizarAcesso();
        return shortLinkRepository.save(link);

    }

    public RedirectView redirecionarUrlOriginal(String hash) {
        ShortLink link = buscarHash(hash);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(link.getUrl().toString());
        return redirectView;


    }
    public ShortLink buscarHash(String hash) {
        return shortLinkRepository.findByHash(hash).orElseThrow(RuntimeException::new);

    }
}
