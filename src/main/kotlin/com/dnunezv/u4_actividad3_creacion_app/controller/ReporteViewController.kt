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
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDate
import java.time.format.DateTimeParseException

@Controller
@RequestMapping("/reportes")
class ReporteViewController(
    private val reporteService: ReporteService,
    private val zonaService: ZonaService,
    private val grupoEtarioService: GrupoEtarioService,
) {

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
