package com.dnunezv.u4_actividad3_creacion_app.service.grupos

import com.dnunezv.u4_actividad3_creacion_app.dto.GrupoEtarioDTO
import com.dnunezv.u4_actividad3_creacion_app.repository.grupos.GrupoEtarioRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GrupoEtarioServiceImpl(
    private val grupoEtarioRepository: GrupoEtarioRepository,
) : GrupoEtarioService {

    @Transactional(readOnly = true)
    override fun findAll(): List<GrupoEtarioDTO> =
        grupoEtarioRepository.findAll(Sort.by("idGrupoEtario")).map { grupoEtario ->
            GrupoEtarioDTO(
                idGrupoEtario = requireNotNull(grupoEtario.idGrupoEtario),
                edadMinima = grupoEtario.edadMinima,
                edadMaxima = grupoEtario.edadMaxima,
                rangoEdad = "[${grupoEtario.edadMinima ?: "?"} - ${grupoEtario.edadMaxima ?: "?"}]",
            )
        }
}
