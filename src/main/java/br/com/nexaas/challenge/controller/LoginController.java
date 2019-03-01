package br.com.nexaas.challenge.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.nexaas.challenge.security.CookieUtil;
import br.com.nexaas.challenge.security.JwtUtil;

/**
 * Login Controller Handler Security
 * 
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    public LoginController() {
        credentials.put("nexaas", "123");
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, RedirectAttributes redirectAttributes){
        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
        	redirectAttributes.addFlashAttribute("error", "Invalid username or password!");
            return "login";
        }

        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "uploadForm";
    }
}
