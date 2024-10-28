package fr.github.springcaas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Qualifier("requestMappingHandlerMapping")
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/")
    public Map<String, List<String>> getEndpoints() {
        Map<String, List<String>> endpoints = new HashMap<>();
        List<String> apiEndpoints = new ArrayList<>();

        handlerMapping.getHandlerMethods().forEach((key, value) -> {
            // Récupère les patterns des URLs
            key.getPatternValues().forEach(pattern -> {
                apiEndpoints.add(key.getMethodsCondition().getMethods().iterator().next()
                        + " " + pattern);
            });
        });

        endpoints.put("endpoints", apiEndpoints);
        return endpoints;
    }
}
