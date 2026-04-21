package com.dnunezv.u4_actividad3_creacion_app.service.origenes

import com.dnunezv.u4_actividad3_creacion_app.dao.fuentes.FuentesDAO
import com.dnunezv.u4_actividad3_creacion_app.dto.AddFuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDescDTO
import com.dnunezv.u4_actividad3_creacion_app.repository.origenes.FuenteRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class FuenteServiceImpl(
    private val fuenteRepository: FuenteRepository,
    private val fuentesDao: FuentesDAO,
) : FuenteService {

    override fun findAll(): List<FuenteDTO> =
        fuenteRepository.findAll(Sort.by("idFuente")).map { fuente ->
            FuenteDTO(
                idFuente = requireNotNull(fuente.idFuente),
                tipoFuente = fuente.tipoFuente?.nombre.orEmpty(),
            )
        }
    override fun findAllWithAdvancedDescription(): List<FuenteDescDTO>
        = fuentesDao.findAllAndDescription()

    override fun addFuente(addFuente: AddFuenteDTO): Int =
        fuentesDao.addFuente(addFuente)
}
