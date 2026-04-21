package com.dnunezv.u4_actividad3_creacion_app.dao.fuentes

import com.dnunezv.u4_actividad3_creacion_app.dto.AddFuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDescDTO

interface FuentesDAO {
    fun findAllAndDescription(): List<FuenteDescDTO>
    fun addFuente(addFuente: AddFuenteDTO): Int
}