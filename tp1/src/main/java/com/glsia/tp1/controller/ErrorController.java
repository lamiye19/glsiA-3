package com.glsia.tp1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Exception during execution on spring application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unkonown error!");
        model.addAttribute("errorMsg", errorMessage);
        return "error";
    }




}
