package dev.yavuztas.boilerplate.springbootwebapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("User Webapp Business Exceptions");

    @Value("${app.user.webservice.url}")
    private String webServiceUrl;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, Object> getResponseAsMap(String response){
        Map map;
        try {
            map = objectMapper.readValue(response, LinkedHashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @ExceptionHandler(UnknownHostException.class)
    public final String handleUnknownHostException(Model model, UnknownHostException exception) {
        logger.error(exception.getMessage(), exception);
        model.addAttribute("title", "Server Error");
        model.addAttribute("message", "Could not connect to server: " + webServiceUrl);
        return "error";
    }

    @ExceptionHandler(ConnectException.class)
    public final String handleConnectException(Model model, ConnectException exception) {
        logger.error(exception.getMessage(), exception);
        model.addAttribute("title", "Server Error");
        model.addAttribute("message", "Could not connect to server: " + webServiceUrl);
        return "error";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final String handleClientErrorException(Model model, HttpClientErrorException exception) {

        logger.warn(exception.getMessage(), exception);

        if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            Map<String, Object> response = getResponseAsMap(exception.getResponseBodyAsString());
            model.addAttribute("title", response.get("status"));
            model.addAttribute("message", response.get("message"));
        }

        if (exception.getStatusCode().is5xxServerError()) {
            model.addAttribute("title", "Server Error");
            model.addAttribute("message", "");
        }

        return "error";
    }

}