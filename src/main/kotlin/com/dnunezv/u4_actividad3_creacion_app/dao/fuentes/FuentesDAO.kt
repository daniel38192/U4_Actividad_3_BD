package com.dnunezv.u4_actividad3_creacion_app.dao.fuentes

import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDescDTO

interface FuentesDAO {
    fun findAllAndDescription(): List<FuenteDescDTO>
}