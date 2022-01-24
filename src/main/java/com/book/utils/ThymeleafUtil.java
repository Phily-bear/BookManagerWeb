package com.book.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtil {
    private static final TemplateEngine engin;
    static {
        engin=new TemplateEngine();
        ClassLoaderTemplateResolver r=new ClassLoaderTemplateResolver();
        r.setCharacterEncoding("utf-8");
        engin.addTemplateResolver(r);
    }

    public static void process(String template, IContext context, Writer writer){
        engin.process(template, context, writer);
    }
}
