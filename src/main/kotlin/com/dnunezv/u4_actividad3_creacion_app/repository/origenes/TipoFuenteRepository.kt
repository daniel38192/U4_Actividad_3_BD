package com.dnunezv.u4_actividad3_creacion_app.repository.origenes

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.TipoFuente
import org.springframework.data.jpa.repository.JpaRepository

interface TipoFuenteRepository : JpaRepository<TipoFuente, Int>
