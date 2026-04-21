package com.dnunezv.u4_actividad3_creacion_app.dao.reportes

import com.dnunezv.u4_actividad3_creacion_app.dto.AddReporteDTO
import com.dnunezv.u4_actividad3_creacion_app.model.ReporteModel
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository

@Repository
class ReportesDAOImpl(val jdbcTemplate: JdbcTemplate): ReportesDAO {

    val namedParameterJdbcTemplate = NamedParameterJdbcTemplate(jdbcTemplate)

    override fun findLastFifteen(): List<ReporteModel>
    = jdbcTemplate.query(
        "SELECT TOP 15 * FROM registros.reportes ORDER BY fecha DESC, idreporte DESC",
        DataClassRowMapper(ReporteModel::class.java)
    )

    override fun addReporteAndGetGeratedID(addReporte: AddReporteDTO): Int {
        val sql = """
            exec registros.crearReporte
                @nombre = :nombre,
                @fecha = :fecha,
                @idzona = :idzona,
                @idgrupoetario = :idgrupoetario,
                @descripcion = :descripcion,
                @evidencia = :evidencia
        """.trimIndent()
        val params = MapSqlParameterSource()
        paramsForAddReporte(params, addReporte)
        return namedParameterJdbcTemplate.queryForObject(sql, params, Int::class.java)
            ?: throw IllegalStateException("No se generó un ID para el reporte")
    }

    private fun paramsForAddReporte(
        params: MapSqlParameterSource,
        addReporte: AddReporteDTO
    ) {
        params.addValue("nombre", addReporte.nombre)
        params.addValue("fecha", addReporte.fecha)
        params.addValue("idzona", addReporte.idZona)
        params.addValue("idgrupoetario", addReporte.idGrupoEtario)
        params.addValue("descripcion", addReporte.descripcion)
        params.addValue("evidencia", addReporte.evidencia)
    }

    override fun deleteReporteByID(idReporte: Int){
        jdbcTemplate.update("exec registros.borrarReporte @idReporte", mapOf("idReporte" to idReporte))
    }

    override fun findAllWithLimitAndOffset(limit: Int, offset: Int): List<ReporteModel>
        = jdbcTemplate.query(
            """
                select *
                from registros.reportes
                order by fecha desc, idReporte desc
                offset ? rows fetch next ? rows only
            """.trimIndent(),
            DataClassRowMapper(ReporteModel::class.java),
            offset, limit
        )

    override fun countAllReportes(): Int
        = jdbcTemplate.queryForObject<Int>(
            "select count(*) from registros.reportes"
    ) ?: 0

    override fun deleteReporte(reporteId: Int) {
        jdbcTemplate.update("exec registros.borrarReporte ?", reporteId)
    }
}
