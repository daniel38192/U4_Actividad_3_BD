package com.dnunezv.u4_actividad3_creacion_app.dao.dashboard

import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DashboardDAOImpl(private val jdbcTemplate: JdbcTemplate): DashboardDAO {
    override fun findLevantamientosPorGrupoEtario(): List<LevantamientoPorGrupoEtarioModel>
        = jdbcTemplate.query(
            "select * from registros.total_levantamientos_por_grupo_etario",
            DataClassRowMapper(LevantamientoPorGrupoEtarioModel::class.java)
        )
    override fun findFuentesAlmacenadas(): List<FuentesAlmacenadasModel>
        = jdbcTemplate.query(
            "select * from registros.total_fuentes_almacenadas",
            DataClassRowMapper(FuentesAlmacenadasModel::class.java)
        )
    override fun findLevantamientosPorEquipo(): List<LevantamientosPorEquipoModel>
        = jdbcTemplate.query(
            "select * from registros.conteo_levantamientos_realizados_por_equipo",
            DataClassRowMapper(LevantamientosPorEquipoModel::class.java)
        )
}

data class LevantamientoPorGrupoEtarioModel(
    val idgrupoetario: Int,
    val rangoedad: String,
    val totallevantamientos: Int,
)

data class FuentesAlmacenadasModel(
    val idtipofuente: Int,
    val nombre: String,
    val totalfuentes: Int,
)

data class LevantamientosPorEquipoModel(
    val idequipo: Int,
    val nombre: String,
    val totallevantamientos: Int,
)
