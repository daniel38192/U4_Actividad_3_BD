package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.dto.AddReporteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.CreateReporteForm
import com.dnunezv.u4_actividad3_creacion_app.service.grupos.GrupoEtarioService
import com.dnunezv.u4_actividad3_creacion_app.service.reportes.ReporteService
import com.dnunezv.u4_actividad3_creacion_app.service.ubicacion.ZonaService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDate
import java.time.format.DateTimeParseException
import kotlin.math.max

@Controller
@RequestMapping("/reportes")
class ReporteViewController(
    private val reporteService: ReporteService,
    private val zonaService: ZonaService,
    private val grupoEtarioService: GrupoEtarioService,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
        private const val MIN_PAGE_SIZE = 1
        private const val MAX_PAGE_SIZE = 50
    }

    @GetMapping
    fun listView(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(name = "page_size", required = false, defaultValue = "$DEFAULT_PAGE_SIZE") pageSizeParam: Int,
    ): ModelAndView {
        val pageSize = normalizePageSize(pageSizeParam)
        val totalReportes = reporteService.countAllReportes()
        val totalPages = max(1, (totalReportes + pageSize - 1) / pageSize)
        val currentPage = page.coerceIn(1, totalPages)
        val offset = (currentPage - 1) * pageSize

        return ModelAndView("reportes-list")
            .addObject("reportes", reporteService.findAllWithLimitAndOffset(pageSize, offset))
            .addObject("totalReportes", totalReportes)
            .addObject("pageSize", pageSize)
            .addObject("currentPage", currentPage)
            .addObject("totalPages", totalPages)
            .addObject("pages", (1..totalPages).toList())
    }

    @GetMapping("/nuevo")
    fun createView(): ModelAndView =
        buildCreateView(CreateReporteForm())

    @PostMapping
    fun create(@ModelAttribute("reporteForm") reporteForm: CreateReporteForm): ModelAndView {
        val errors = validate(reporteForm)
        if (errors.isNotEmpty()) {
            return buildCreateView(reporteForm)
                .addObject("errors", errors)
        }

        reporteService.createReporte(
            AddReporteDTO(
                nombre = reporteForm.nombre.trim(),
                fecha = LocalDate.parse(reporteForm.fecha),
                idZona = reporteForm.idZona!!,
                idGrupoEtario = reporteForm.idGrupoEtario!!,
                descripcion = reporteForm.descripcion.trim().ifBlank { null },
                evidencia = reporteForm.evidencia.trim(),
            ),
            reporteForm.idFuentes.toList(),
            reporteForm.idEquipos.toList(),
        )

        return ModelAndView("redirect:/dashboard?created=true")
    }

    private fun buildCreateView(reporteForm: CreateReporteForm): ModelAndView =
        ModelAndView("reportes-create")
            .addObject("reporteForm", reporteForm)
            .addObject("zonas", zonaService.findAll())
            .addObject("gruposEtarios", grupoEtarioService.findAll())

    private fun normalizePageSize(pageSize: Int): Int =
        if (pageSize in MIN_PAGE_SIZE..MAX_PAGE_SIZE) pageSize else DEFAULT_PAGE_SIZE

    private fun validate(reporteForm: CreateReporteForm): List<String> {
        val errors = mutableListOf<String>()

        if (reporteForm.nombre.isBlank()) {
            errors.add("El nombre del reporte es obligatorio.")
        }

        if (reporteForm.fecha.isBlank()) {
            errors.add("La fecha del reporte es obligatoria.")
        } else {
            try {
                LocalDate.parse(reporteForm.fecha)
            } catch (_: DateTimeParseException) {
                errors.add("La fecha del reporte no tiene un formato valido.")
            }
        }

        if (reporteForm.idZona == null) {
            errors.add("Debes seleccionar una zona.")
        }

        if (reporteForm.idGrupoEtario == null) {
            errors.add("Debes seleccionar un grupo etario.")
        }

        if (reporteForm.evidencia.isBlank()) {
            errors.add("La evidencia es obligatoria.")
        }

        return errors
    }
}
