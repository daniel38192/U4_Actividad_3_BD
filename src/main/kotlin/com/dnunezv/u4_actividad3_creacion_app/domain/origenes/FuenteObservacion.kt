package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "fuente_observacion", schema = "origenes")
class FuenteObservacion(
    @EmbeddedId
    var id: FuenteObservacionId = FuenteObservacionId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFuente")
    @JoinColumn(name = "idfuente", nullable = false)
    var fuente: Fuente? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idObservacion")
    @JoinColumn(name = "idobservacion", nullable = false)
    var observacion: Observacion? = null,
)
