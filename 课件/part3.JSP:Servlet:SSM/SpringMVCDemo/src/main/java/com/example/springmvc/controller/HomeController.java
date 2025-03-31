package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("message", "欢迎来到Spring MVC学习示例！");
        model.addAttribute("who", "张三");

        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
} 