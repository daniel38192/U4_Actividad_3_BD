package com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class LevantamientoPorEquipoId(
    @Column(name = "idlevantamiento")
    var idLevantamiento: Int? = null,

    @Column(name = "idequipo")
    var idEquipo: Int? = null,
) : Serializable
