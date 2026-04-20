package com.dnunezv.u4_actividad3_creacion_app.domain.registros

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.Fuente
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "problematica_fuente", schema = "registros")
class ProblematicaFuente(
    @EmbeddedId
    var id: ProblematicaFuenteId = ProblematicaFuenteId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProblematica")
    @JoinColumn(name = "idproblematica", nullable = false)
    var problematica: Problematica? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFuente")
    @JoinColumn(name = "idfuente", nullable = false)
    var fuente: Fuente? = null,
)
