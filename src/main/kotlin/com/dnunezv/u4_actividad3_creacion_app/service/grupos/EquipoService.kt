package com.dnunezv.u4_actividad3_creacion_app.service.grupos

import com.dnunezv.u4_actividad3_creacion_app.dto.EquipoDTO

interface EquipoService {
    fun findAll(): List<EquipoDTO>
}
