package com.dnunezv.u4_actividad3_creacion_app.domain.grupos

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "grupo_etario", schema = "grupos")
class GrupoEtario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupoetario")
    var idGrupoEtario: Int? = null,

    @Column(name = "edadminima")
    var edadMinima: Int? = null,

    @Column(name = "edadmaxima")
    var edadMaxima: Int? = null,
)
