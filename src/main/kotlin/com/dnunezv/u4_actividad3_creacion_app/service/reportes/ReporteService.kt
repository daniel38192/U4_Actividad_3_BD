package com.dnunezv.u4_actividad3_creacion_app.service.reportes

import com.dnunezv.u4_actividad3_creacion_app.dto.AddReporteDTO
import com.dnunezv.u4_actividad3_creacion_app.model.ReporteModel

interface ReporteService {
    fun findLastFifteen(): List<ReporteModel>
    fun addReporte(addReporte: AddReporteDTO): Int
    fun createReporte(addReporte: AddReporteDTO, idFuentes: List<Int>, idEquipos: List<Int>): Int
}
