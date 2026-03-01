package org.vs.documents.postcards.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalErrorController implements ErrorController {

    /**
     * Catches all unhandled errors (404, 500, etc.)
     * and strictly redirects the user to the home page.
     */
    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/";
    }
}
