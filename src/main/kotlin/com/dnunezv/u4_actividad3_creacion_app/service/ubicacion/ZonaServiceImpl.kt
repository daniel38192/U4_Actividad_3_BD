package com.dnunezv.u4_actividad3_creacion_app.service.ubicacion

import com.dnunezv.u4_actividad3_creacion_app.dto.ZonaDTO
import com.dnunezv.u4_actividad3_creacion_app.repository.ubicacion.ZonaRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ZonaServiceImpl(
    private val zonaRepository: ZonaRepository,
) : ZonaService {

    @Transactional(readOnly = true)
    override fun findAll(): List<ZonaDTO> =
        zonaRepository.findAll(Sort.by("idZona")).map { zona ->
            ZonaDTO(
                idZona = requireNotNull(zona.idZona),
                nombre = zona.nombre.orEmpty(),
                descripcion = zona.descripcion,
            )
        }
}
