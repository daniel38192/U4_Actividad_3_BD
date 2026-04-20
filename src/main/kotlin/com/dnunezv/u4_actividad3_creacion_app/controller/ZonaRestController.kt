package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.dto.ZonaDTO
import com.dnunezv.u4_actividad3_creacion_app.service.ubicacion.ZonaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/zonas")
class ZonaRestController(
    private val zonaService: ZonaService,
) {
    @GetMapping
    fun findAll(): List<ZonaDTO> = zonaService.findAll()
}
