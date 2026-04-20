package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "entrevista", schema = "origenes")
class Entrevista(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identrevista")
    var idEntrevista: Int? = null,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String? = null,
)
