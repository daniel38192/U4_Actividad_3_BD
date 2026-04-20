package com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion

import com.dnunezv.u4_actividad3_creacion_app.domain.grupos.GrupoEtario
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "levantamiento", schema = "ubicacion")
class Levantamiento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlevantamiento")
    var idLevantamiento: Int? = null,

    @Column(name = "fecha", nullable = false)
    var fecha: LocalDate? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idzona", nullable = false)
    var zona: Zona? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgrupoetario", nullable = false)
    var grupoEtario: GrupoEtario? = null,
)
