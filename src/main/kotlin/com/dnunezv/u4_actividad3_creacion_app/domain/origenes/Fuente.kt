package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

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
@Table(name = "fuente", schema = "origenes")
class Fuente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfuente")
    var idFuente: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipofuente", nullable = false)
    var tipoFuente: TipoFuente? = null,
)
