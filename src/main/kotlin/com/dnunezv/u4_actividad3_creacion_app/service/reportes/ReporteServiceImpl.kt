package com.dnunezv.u4_actividad3_creacion_app.service.reportes

import com.dnunezv.u4_actividad3_creacion_app.dto.AddReporteDTO
import com.dnunezv.u4_actividad3_creacion_app.dao.reportes.ReportesDAO
import com.dnunezv.u4_actividad3_creacion_app.domain.registros.ProblematicaFuente
import com.dnunezv.u4_actividad3_creacion_app.domain.registros.ProblematicaFuenteId
import com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion.LevantamientoPorEquipo
import com.dnunezv.u4_actividad3_creacion_app.domain.ubicacion.LevantamientoPorEquipoId
import com.dnunezv.u4_actividad3_creacion_app.model.ReporteModel
import com.dnunezv.u4_actividad3_creacion_app.repository.grupos.EquipoRepository
import com.dnunezv.u4_actividad3_creacion_app.repository.origenes.FuenteRepository
import com.dnunezv.u4_actividad3_creacion_app.repository.registros.ProblematicaFuenteRepository
import com.dnunezv.u4_actividad3_creacion_app.repository.registros.ProblematicaRepository
import com.dnunezv.u4_actividad3_creacion_app.repository.ubicacion.LevantamientoPorEquipoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional(rollbackOn = [Exception::class])
class ReporteServiceImpl(
    val reportesDAO: ReportesDAO,
    private val problematicaRepository: ProblematicaRepository,
    private val fuenteRepository: FuenteRepository,
    private val equipoRepository: EquipoRepository,
    private val problematicaFuenteRepository: ProblematicaFuenteRepository,
    private val levantamientoPorEquipoRepository: LevantamientoPorEquipoRepository,
): ReporteService {

    override fun findLastFifteen(): List<ReporteModel>
        = reportesDAO.findLastFifteen()

    override fun addReporte(addReporte: AddReporteDTO): Int
        = reportesDAO.addReporteAndGetGeratedID(addReporte)

    override fun createReporte(addReporte: AddReporteDTO, idFuentes: List<Int>, idEquipos: List<Int>): Int {
        val idReporte = reportesDAO.addReporteAndGetGeratedID(addReporte)
        val problematica = problematicaRepository.getReferenceById(idReporte)
        val levantamiento = requireNotNull(problematica.levantamiento) {
            "El reporte $idReporte no tiene levantamiento asociado"
        }

        idFuentes.distinct().forEach { idFuente ->
            val fuente = fuenteRepository.getReferenceById(idFuente)
            problematicaFuenteRepository.save(
                ProblematicaFuente(
                    id = ProblematicaFuenteId(
                        idProblematica = idReporte,
                        idFuente = idFuente,
                    ),
                    problematica = problematica,
                    fuente = fuente,
                )
            )
        }
        idEquipos.distinct().forEach { idEquipo ->
            val equipo = equipoRepository.getReferenceById(idEquipo)
            levantamientoPorEquipoRepository.save(
                LevantamientoPorEquipo(
                    id = LevantamientoPorEquipoId(
                        idLevantamiento = levantamiento.idLevantamiento,
                        idEquipo = idEquipo,
                    ),
                    levantamiento = levantamiento,
                    equipo = equipo,
                )
            )
        }
        return idReporte
    }
}
