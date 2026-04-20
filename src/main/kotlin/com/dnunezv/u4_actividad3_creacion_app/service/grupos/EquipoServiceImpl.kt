package com.dnunezv.u4_actividad3_creacion_app.service.grupos

import com.dnunezv.u4_actividad3_creacion_app.dto.EquipoDTO
import com.dnunezv.u4_actividad3_creacion_app.repository.grupos.EquipoRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EquipoServiceImpl(
    private val equipoRepository: EquipoRepository,
) : EquipoService {

    @Transactional(readOnly = true)
    override fun findAll(): List<EquipoDTO> =
        equipoRepository.findAll(Sort.by("idEquipo")).map { equipo ->
            EquipoDTO(
                idEquipo = requireNotNull(equipo.idEquipo),
                nombre = equipo.nombre.orEmpty(),
                descripcion = equipo.descripcion,
            )
        }
}
