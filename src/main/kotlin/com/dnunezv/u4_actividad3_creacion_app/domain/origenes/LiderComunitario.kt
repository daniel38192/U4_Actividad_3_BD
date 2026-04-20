package com.dnunezv.u4_actividad3_creacion_app.domain.origenes

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "lider_comunitario", schema = "origenes")
class LiderComunitario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlidercomunitario")
    var idLiderComunitario: Int? = null,

    @Column(name = "nombrelider", nullable = false, length = 50)
    var nombreLider: String? = null,
)
