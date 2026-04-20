package com.dnunezv.u4_actividad3_creacion_app.service.grupos

import com.dnunezv.u4_actividad3_creacion_app.dto.GrupoEtarioDTO

interface GrupoEtarioService {
    fun findAll(): List<GrupoEtarioDTO>
}
