package com.dnunezv.u4_actividad3_creacion_app.repository.grupos

import com.dnunezv.u4_actividad3_creacion_app.domain.grupos.GrupoEtario
import org.springframework.data.jpa.repository.JpaRepository

interface GrupoEtarioRepository : JpaRepository<GrupoEtario, Int>
