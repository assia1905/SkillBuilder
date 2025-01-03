package com.example.micro__Learning.controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404"; // page personnalisée pour 404
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403"; // page personnalisée pour 403
            }
        }
        return "error"; // page générique d'erreur
    }

    public String getErrorPath() {
        return "/error";
    }
}
