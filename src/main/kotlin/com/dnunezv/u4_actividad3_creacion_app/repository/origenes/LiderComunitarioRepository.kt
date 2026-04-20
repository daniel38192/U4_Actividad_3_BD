package com.dnunezv.u4_actividad3_creacion_app.repository.origenes

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.LiderComunitario
import org.springframework.data.jpa.repository.JpaRepository

interface LiderComunitarioRepository : JpaRepository<LiderComunitario, Int>
