package com.dnunezv.u4_actividad3_creacion_app.service.dashboard

import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.FuentesAlmacenadasModel
import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.LevantamientoPorGrupoEtarioModel
import com.dnunezv.u4_actividad3_creacion_app.dao.dashboard.LevantamientosPorEquipoModel

interface DashboardService {
    fun findLevantamientosPorGrupoEtario(): List<LevantamientoPorGrupoEtarioModel>
    fun findFuentesAlmacenadas(): List<FuentesAlmacenadasModel>
    fun findLevantamientosPorEquipo(): List<LevantamientosPorEquipoModel>
}
