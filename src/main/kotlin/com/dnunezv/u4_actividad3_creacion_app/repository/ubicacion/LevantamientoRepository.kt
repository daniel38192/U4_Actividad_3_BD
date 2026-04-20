package com.dnunezv.u4_actividad3_creacion_app.repository.ubicacion

import com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion.Levantamiento
import org.springframework.data.jpa.repository.JpaRepository

interface LevantamientoRepository : JpaRepository<Levantamiento, Int>
