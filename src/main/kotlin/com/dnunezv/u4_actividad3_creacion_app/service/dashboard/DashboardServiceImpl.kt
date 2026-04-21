package com.dnunezv.u4_actividad3_creacion_app.service.dashboard

import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.DashboardDAO
import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.FuentesAlmacenadasModel
import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.LevantamientoPorGrupoEtarioModel
import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.LevantamientosPorEquipoModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DashboardServiceImpl(
    private val dashboardDAO: DashboardDAO,
) : DashboardService {

    @Transactional(readOnly = true)
    override fun findLevantamientosPorGrupoEtario(): List<LevantamientoPorGrupoEtarioModel> =
        dashboardDAO.findLevantamientosPorGrupoEtario()

    @Transactional(readOnly = true)
    override fun findFuentesAlmacenadas(): List<FuentesAlmacenadasModel> =
        dashboardDAO.findFuentesAlmacenadas()

    @Transactional(readOnly = true)
    override fun findLevantamientosPorEquipo(): List<LevantamientosPorEquipoModel> =
        dashboardDAO.findLevantamientosPorEquipo()
}
