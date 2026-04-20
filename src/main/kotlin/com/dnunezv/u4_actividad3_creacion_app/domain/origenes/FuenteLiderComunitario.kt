package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "fuente_lider_comunitario", schema = "origenes")
class FuenteLiderComunitario(
    @EmbeddedId
    var id: FuenteLiderComunitarioId = FuenteLiderComunitarioId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFuente")
    @JoinColumn(name = "idfuente", nullable = false)
    var fuente: Fuente? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLiderComunitario")
    @JoinColumn(name = "idlidercomunitario", nullable = false)
    var liderComunitario: LiderComunitario? = null,
)
