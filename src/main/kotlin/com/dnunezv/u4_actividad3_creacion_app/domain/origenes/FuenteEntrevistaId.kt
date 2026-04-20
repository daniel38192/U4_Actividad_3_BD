package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class FuenteEntrevistaId(
    @Column(name = "idfuente")
    var idFuente: Int? = null,

    @Column(name = "identrevista")
    var idEntrevista: Int? = null,
) : Serializable
