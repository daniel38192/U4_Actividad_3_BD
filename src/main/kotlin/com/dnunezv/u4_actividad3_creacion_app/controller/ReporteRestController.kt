package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.service.reportes.ReporteService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/reporte")
class ReporteRestController(
    val reporteService: ReporteService
) {
    @DeleteMapping("/{reporteId}")
    fun delete(@PathVariable reporteId: Int)
        = reporteService.deleteReporte(reporteId)
}
