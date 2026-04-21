package com.dnunezv.u4_actividad3_creacion_app.service.origenes

import com.dnunezv.u4_actividad3_creacion_app.dto.AddFuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDescDTO

interface FuenteService {
    fun findAll(): List<FuenteDTO>
    fun findAllWithAdvancedDescription(): List<FuenteDescDTO>
    fun addFuente(addFuente: AddFuenteDTO): Int
}
