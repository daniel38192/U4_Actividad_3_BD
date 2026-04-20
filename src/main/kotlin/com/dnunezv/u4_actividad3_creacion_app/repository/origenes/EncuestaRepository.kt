package com.dnunezv.u4_actividad3_creacion_app.repository.origenes

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.Encuesta
import org.springframework.data.jpa.repository.JpaRepository

interface EncuestaRepository : JpaRepository<Encuesta, Int>
