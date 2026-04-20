package com.dnunezv.u4_actividad3_creacion_app.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController {
    @GetMapping
    fun login() = "login"
}
