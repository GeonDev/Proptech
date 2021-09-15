package com.apt.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenGraphProtocol {
    String title;
    String url;
    String type;
    String image;
}
