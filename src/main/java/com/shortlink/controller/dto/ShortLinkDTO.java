package com.shortlink.controller.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Data
public class ShortLinkDTO {

    @NotNull
    private URL url;

}
