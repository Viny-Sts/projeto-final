package br.edu.ifg.luziania.model.util;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate
public class ErrorTemplate {
    public static native TemplateInstance forbidden();
}
