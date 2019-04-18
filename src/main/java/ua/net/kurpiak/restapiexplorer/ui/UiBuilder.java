package ua.net.kurpiak.restapiexplorer.ui;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.net.kurpiak.restapiexplorer.pojo.ControllerDescription;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class UiBuilder {

    private static final Logger LOGGER = LogManager.getLogger(UiBuilder.class);

    private static final String ROOT_DIR = "/rest-api-explorer";

    private static final String TEMPLATE_LOAD_ERROR = "Can't load template";

    private static final String TEMPLATE_PROCESSING_ERROR = "Can't process template";

    public static void build(List<ControllerDescription> controllers) {
        Configuration configuration = initFreemarker();

        try {
            Template template = configuration.getTemplate("page.ftlh");

            Writer writer = new OutputStreamWriter(System.out);
            template.process(new HashMap<>(), writer);
        } catch (IOException e) {
            LOGGER.error(TEMPLATE_LOAD_ERROR, e);
            throw new RuntimeException(TEMPLATE_LOAD_ERROR, e);
        } catch (TemplateException e) {
            LOGGER.error(TEMPLATE_PROCESSING_ERROR, e);
            throw new RuntimeException(TEMPLATE_PROCESSING_ERROR, e);
        }
    }

    private static Configuration initFreemarker() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setTemplateLoader(new ClassTemplateLoader(UiBuilder.class, ROOT_DIR));

        return cfg;
    }

}
