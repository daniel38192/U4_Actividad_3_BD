package com.dnunezv.u4_actividad3_creacion_app.repository.origenes

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.Fuente
import org.springframework.data.jpa.repository.JpaRepository

interface FuenteRepository : JpaRepository<Fuente, Int>
