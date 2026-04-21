package com.dnunezv.u4_actividad3_creacion_app.dao.dashboard

interface DashboardDAO {
    fun findLevantamientosPorGrupoEtario(): List<LevantamientoPorGrupoEtarioModel>
    fun findFuentesAlmacenadas(): List<FuentesAlmacenadasModel>
    fun findLevantamientosPorEquipo(): List<LevantamientosPorEquipoModel>
}