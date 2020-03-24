package dev.yavuztas.boilerplate.springbootwebapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yavuztas.boilerplate.springbootwebapp.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Error handler for our business logic errors.
 *
 * @author Yavuz Tas
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("User Webapp Business Exceptions");

    @Value("${app.user.webservice.url}")
    private String webServiceUrl;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, Object> getResponseAsMap(String response) {
        try {
            return objectMapper.readValue(response, LinkedHashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler({
            UnknownHostException.class,
            ConnectException.class
    })
    public final String handleConnectionException(@AuthenticationPrincipal User user, Model model, Exception exception) {
        logger.error(exception.getMessage(), exception);
        model.addAttribute("user", user);
        model.addAttribute("customError", true);
        model.addAttribute("title", "API Error");
        model.addAttribute("message", "Could not connect to server: " + webServiceUrl);
        return "error";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final String handleClientErrorException(@AuthenticationPrincipal User user, Model model, HttpClientErrorException exception) {

        logger.warn(exception.getMessage(), exception);
        model.addAttribute("user", user);
        model.addAttribute("customError", true);

        if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            Map<String, Object> response = getResponseAsMap(exception.getResponseBodyAsString());
            model.addAttribute("title", response.get("status"));
            model.addAttribute("message", response.get("message"));
        }

        if (exception.getStatusCode().is5xxServerError()) {
            model.addAttribute("title", "API Error");
            model.addAttribute("message", "Error on remote server");
        }

        return "error";
    }

}