package com.dnunezv.u4_actividad3_creacion_app.repository.registros

import com.dnunezv.u4_actividad3_creacion_app.domain.registros.Problematica
import org.springframework.data.jpa.repository.JpaRepository

interface ProblematicaRepository : JpaRepository<Problematica, Int>
