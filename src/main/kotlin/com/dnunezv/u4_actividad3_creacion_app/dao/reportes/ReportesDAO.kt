package com.dnunezv.u4_actividad3_creacion_app.dao.reportes

import com.dnunezv.u4_actividad3_creacion_app.dto.AddReporteDTO
import com.dnunezv.u4_actividad3_creacion_app.model.ReporteModel

interface ReportesDAO {
    fun findLastFifteen(): List<ReporteModel>
    fun addReporteAndGetGeratedID(addReporte: AddReporteDTO): Int
    fun deleteReporteByID(idReporte: Int)
}
