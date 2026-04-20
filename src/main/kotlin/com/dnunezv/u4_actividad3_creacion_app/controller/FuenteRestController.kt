package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDescDTO
import com.dnunezv.u4_actividad3_creacion_app.service.origenes.FuenteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fuentes")
class FuenteRestController(
    private val fuenteService: FuenteService,
) {
    @GetMapping
    fun findAll(): List<FuenteDTO> = fuenteService.findAll()

    @GetMapping("/description")
    fun findAllAndDescription(): List<FuenteDescDTO> = fuenteService.findAllWithAdvancedDescription()
}
