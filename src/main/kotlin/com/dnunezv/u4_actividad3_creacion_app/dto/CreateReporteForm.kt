package com.dnunezv.u4_actividad3_creacion_app.dto

import java.time.LocalDate

data class CreateReporteForm(
    var nombre: String = "",
    var fecha: String = LocalDate.now().toString(),
    var idZona: Int? = null,
    var idGrupoEtario: Int? = null,
    var idFuentes: MutableList<Int> = mutableListOf(),
    var idEquipos: MutableList<Int> = mutableListOf(),
    var descripcion: String = "",
    var evidencia: String = "",
)
