package com.dnunezv.u4_actividad3_creacion_app.dto

import java.time.LocalDate

data class AddReporteDTO(
    val nombre: String,
    val fecha: LocalDate,
    val idZona: Int,
    val idGrupoEtario: Int,
    val descripcion: String?,
    val evidencia: String
)
