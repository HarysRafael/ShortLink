package com.shortlink.controller.dto;

import lombok.Data;
import java.net.URL;

@Data
public class ShortLinkResponse {

    private String id;
    private URL url;
    private String shortlink;
    private Long acessos;

}
