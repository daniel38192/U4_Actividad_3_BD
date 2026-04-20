package com.dnunezv.u4_actividad3_creacion_app.model

import java.time.LocalDate

data class ReporteModel(
    val idReporte: Int,
    val descripcion: String?,
    val evidencia: String,
    val fecha: LocalDate,
    val grupoEtario: String,
    val zona: String,
    val equipos: String?,
    val fuente: String?
)
