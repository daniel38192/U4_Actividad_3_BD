package com.dnunezv.u4_actividad3_creacion_app.dao.fuentes

import com.dnunezv.u4_actividad3_creacion_app.domain.origenes.Fuente
import com.dnunezv.u4_actividad3_creacion_app.dto.AddFuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.FuenteDescDTO
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository

@Repository
class FuentesDAOImpl(var jdbcTemplate: JdbcTemplate): FuentesDAO {
    override fun findAllAndDescription(): List<FuenteDescDTO> {
        val sql = "select f.idfuente, tf.nombre as tipo,\n" +
                "       concat(\n" +
                "       coalesce(string_agg(e.nombre, ', '), ''),\n" +
                "       coalesce(string_agg(e2.nombre, ', '), ''),\n" +
                "       coalesce(string_agg(lc.nombrelider, ', '), ''),\n" +
                "       coalesce(string_agg(o.observacion, ', '), '')\n" +
                "       ) as descripcion\n" +
                "from origenes.fuente f\n" +
                "join origenes.tipo_fuente tf on f.idtipofuente = tf.idtipofuente\n" +
                "left join origenes.fuente_encuesta fe on f.idfuente = fe.idfuente\n" +
                "left join origenes.encuesta e on fe.idencuesta = e.idencuesta\n" +
                "left join origenes.fuente_entrevista fe2 on f.idfuente = fe2.idfuente\n" +
                "left join origenes.entrevista e2 on fe2.identrevista = e2.identrevista\n" +
                "left join origenes.fuente_lider_comunitario flc on f.idfuente = flc.idfuente\n" +
                "left join origenes.lider_comunitario lc on flc.idlidercomunitario = lc.idlidercomunitario\n" +
                "left join origenes.fuente_observacion fo on f.idfuente = fo.idfuente\n" +
                "left join origenes.observacion o on fo.idobservacion = o.idobservacion\n" +
                "group by f.idfuente, tf.nombre;"
        return jdbcTemplate.query(sql, DataClassRowMapper(FuenteDescDTO::class.java))
    }

    override fun addFuente(addFuente: AddFuenteDTO): Int
        = jdbcTemplate.queryForObject<Int>(
        "exec origenes.crearFuente ?, ?",
        addFuente.idTipoFuente, addFuente.nombre) ?: throw RuntimeException("Failed to add fuente")
}