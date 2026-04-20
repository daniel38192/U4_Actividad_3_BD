package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Entity
@Table(name = "observacion", schema = "origenes")
class Observacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idobservacion")
    var idObservacion: Int? = null,

    @Lob
    @Column(name = "observacion")
    var observacion: String? = null,
)
