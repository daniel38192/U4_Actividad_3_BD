package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.dto.GrupoEtarioDTO
import com.dnunezv.u4_actividad3_creacion_app.service.grupos.GrupoEtarioService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/grupos-etarios")
class GrupoEtarioRestController(
    private val grupoEtarioService: GrupoEtarioService,
) {
    @GetMapping
    fun findAll(): List<GrupoEtarioDTO> = grupoEtarioService.findAll()
}
