package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "encuesta", schema = "origenes")
class Encuesta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idencuesta")
    var idEncuesta: Int? = null,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String? = null,
)
