package com.dnunezv.u4_actividad3_creacion_app.service.ubicacion

import com.dnunezv.u4_actividad3_creacion_app.dto.ZonaDTO

interface ZonaService {
    fun findAll(): List<ZonaDTO>
}
