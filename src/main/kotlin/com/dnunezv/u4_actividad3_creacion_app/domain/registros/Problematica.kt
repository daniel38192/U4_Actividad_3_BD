package com.dnunezv.u4_actividad3_creacion_app.domain.registros

import com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion.Levantamiento
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "problematica", schema = "registros")
class Problematica(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproblematica")
    var idProblematica: Int? = null,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlevantamiento", nullable = false)
    var levantamiento: Levantamiento? = null,

    @Column(name = "descripcion", length = 255)
    var descripcion: String? = null,

    @Column(name = "evidencia", nullable = false, length = 255)
    var evidencia: String? = null,
)
