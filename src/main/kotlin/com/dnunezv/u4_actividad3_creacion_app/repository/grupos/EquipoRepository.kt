package com.dnunezv.u4_actividad3_creacion_app.repository.grupos

import com.dnunezv.u4_actividad3_creacion_app.domain.grupos.Equipo
import org.springframework.data.jpa.repository.JpaRepository

interface EquipoRepository : JpaRepository<Equipo, Int>
