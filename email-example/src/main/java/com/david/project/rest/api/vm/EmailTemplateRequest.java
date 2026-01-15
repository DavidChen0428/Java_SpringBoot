package com.david.project.rest.api.vm;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class EmailTemplateRequest {

    private String to;

    private String subject;

    private Map<String, Object> model;
}
