package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.service.reportes.ReporteService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/dashboard")
class DashboardController(val reportesService: ReporteService) {
    @GetMapping
    fun dashboard(@RequestParam(required = false, defaultValue = "false") created: Boolean)
        = ModelAndView("dashboard")
        .addObject("lastReportes", reportesService.findLastFifteen())
        .addObject("created", created)

}
