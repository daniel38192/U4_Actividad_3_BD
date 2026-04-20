package com.dnunezv.u4_actividad3_creacion_app.dto

data class GrupoEtarioDTO(
    val idGrupoEtario: Int,
    val edadMinima: Int?,
    val edadMaxima: Int?,
    val rangoEdad: String,
)
