package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.dto.EquipoDTO
import com.dnunezv.u4_actividad3_creacion_app.service.grupos.EquipoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/equipos")
class EquipoRestController(
    private val equipoService: EquipoService,
) {
    @GetMapping
    fun findAll(): List<EquipoDTO> = equipoService.findAll()
}
