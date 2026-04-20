package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class FuenteEncuestaId(
    @Column(name = "idfuente")
    var idFuente: Int? = null,

    @Column(name = "idencuesta")
    var idEncuesta: Int? = null,
) : Serializable
