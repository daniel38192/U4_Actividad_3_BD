package com.dnunezv.u4_actividad3_creacion_app.repository.ubicacion

import com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion.Zona
import org.springframework.data.jpa.repository.JpaRepository

interface ZonaRepository : JpaRepository<Zona, Int>
