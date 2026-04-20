package com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "zona", schema = "ubicacion")
class Zona(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idzona")
    var idZona: Int? = null,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String? = null,

    @Column(name = "descripcion", length = 255)
    var descripcion: String? = null,
)
