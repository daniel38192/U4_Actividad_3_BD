package com.dnunezv.u4_actividad3_creacion_app.domain.registros

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ProblematicaFuenteId(
    @Column(name = "idproblematica")
    var idProblematica: Int? = null,

    @Column(name = "idfuente")
    var idFuente: Int? = null,
) : Serializable
