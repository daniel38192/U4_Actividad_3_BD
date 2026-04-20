package com.dnunezv.u4_actividad3_creacion_app.domain.grupos

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "equipo", schema = "grupos")
class Equipo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idequipo")
    var idEquipo: Int? = null,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String? = null,

    @Column(name = "descripcion", length = 255)
    var descripcion: String? = null,
)
