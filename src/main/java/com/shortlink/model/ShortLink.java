package com.shortlink.model;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.net.URL;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class ShortLink {

    @Id
    private String id;
    @NotNull
    private URL url;
    private String hash;
    private Long acessos;

    public void atualizarAcesso(){
        this.acessos= this.acessos+1L;
    }
}
