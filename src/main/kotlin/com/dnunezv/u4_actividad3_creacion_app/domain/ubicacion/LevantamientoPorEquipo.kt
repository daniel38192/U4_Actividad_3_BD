package com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion

import com.dnunezv.u4_actividad3_creacion_app.domain.grupos.Equipo
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "levantamientos_por_equipo", schema = "ubicacion")
class LevantamientoPorEquipo(
    @EmbeddedId
    var id: LevantamientoPorEquipoId = LevantamientoPorEquipoId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLevantamiento")
    @JoinColumn(name = "idlevantamiento", nullable = false)
    var levantamiento: Levantamiento? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEquipo")
    @JoinColumn(name = "idequipo", nullable = false)
    var equipo: Equipo? = null,
)
