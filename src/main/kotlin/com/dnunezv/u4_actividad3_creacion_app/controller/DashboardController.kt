package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.service.reportes.ReporteService
import com.dnunezv.u4_actividad3_creacion_app.service.dashboard.DashboardService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import kotlin.math.max

@Controller
@RequestMapping("/dashboard")
class DashboardController(
    private val reportesService: ReporteService,
    private val dashboardService: DashboardService,
) {
    @GetMapping
    fun dashboard(@RequestParam(required = false, defaultValue = "false") created: Boolean): ModelAndView {
        val levantamientosPorGrupoEtario = dashboardService.findLevantamientosPorGrupoEtario()
        val fuentesAlmacenadas = dashboardService.findFuentesAlmacenadas()
        val levantamientosPorEquipo = dashboardService.findLevantamientosPorEquipo()

        return ModelAndView("dashboard")
            .addObject("lastReportes", reportesService.findLastFifteen())
            .addObject("totalReportes", reportesService.countAllReportes())
            .addObject("levantamientosPorGrupoEtario", levantamientosPorGrupoEtario)
            .addObject("fuentesAlmacenadas", fuentesAlmacenadas)
            .addObject("levantamientosPorEquipo", levantamientosPorEquipo)
            .addObject(
                "maxLevantamientosGrupoEtario",
                max(1, levantamientosPorGrupoEtario.maxOfOrNull { it.totallevantamientos } ?: 0)
            )
            .addObject(
                "maxFuentesAlmacenadas",
                max(1, fuentesAlmacenadas.maxOfOrNull { it.totalfuentes } ?: 0)
            )
            .addObject(
                "maxLevantamientosEquipo",
                max(1, levantamientosPorEquipo.maxOfOrNull { it.totallevantamientos } ?: 0)
            )
            .addObject("created", created)
    }

}
