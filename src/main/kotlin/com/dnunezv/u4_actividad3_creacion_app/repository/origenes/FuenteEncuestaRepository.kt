package com.dnunezv.u4_actividad3_creacion_app.repository.origenes

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.FuenteEncuesta
import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.FuenteEncuestaId
import org.springframework.data.jpa.repository.JpaRepository

interface FuenteEncuestaRepository : JpaRepository<FuenteEncuesta, FuenteEncuestaId>
