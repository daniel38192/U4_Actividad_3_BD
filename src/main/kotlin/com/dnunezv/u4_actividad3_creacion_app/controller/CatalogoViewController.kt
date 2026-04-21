package com.dnunezv.u4_actividad3_creacion_app.controller

import com.dnunezv.u4_actividad3_creacion_app.domain.grupos.Equipo
import com.dnunezv.u4_actividad3_creacion_app.dto.AddFuenteDTO
import com.dnunezv.u4_actividad3_creacion_app.dto.CreateEquipoForm
import com.dnunezv.u4_actividad3_creacion_app.dto.CreateFuenteForm
import com.dnunezv.u4_actividad3_creacion_app.repository.grupos.EquipoRepository
import com.dnunezv.u4_actividad3_creacion_app.repository.origenes.TipoFuenteRepository
import com.dnunezv.u4_actividad3_creacion_app.service.origenes.FuenteService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class CatalogoViewController(
    private val equipoRepository: EquipoRepository,
    private val tipoFuenteRepository: TipoFuenteRepository,
    private val fuenteService: FuenteService,
) {

    @GetMapping("/equipos/nuevo")
    fun createEquipoView(@RequestParam(required = false, defaultValue = "false") created: Boolean): ModelAndView =
        buildEquipoView(CreateEquipoForm())
            .addObject("created", created)

    @PostMapping("/equipos")
    fun createEquipo(@ModelAttribute("equipoForm") equipoForm: CreateEquipoForm): ModelAndView {
        val errors = validateEquipo(equipoForm)
        if (errors.isNotEmpty()) {
            return buildEquipoView(equipoForm)
                .addObject("errors", errors)
        }

        equipoRepository.save(
            Equipo(
                nombre = equipoForm.nombre.trim(),
                descripcion = equipoForm.descripcion.trim().ifBlank { null },
            )
        )

        return ModelAndView("redirect:/equipos/nuevo?created=true")
    }

    @GetMapping("/fuentes/nuevo")
    fun createFuenteView(@RequestParam(required = false, defaultValue = "false") created: Boolean): ModelAndView =
        buildFuenteView(CreateFuenteForm())
            .addObject("created", created)

    @PostMapping("/fuentes")
    fun createFuente(@ModelAttribute("fuenteForm") fuenteForm: CreateFuenteForm): ModelAndView {
        val errors = validateFuente(fuenteForm)
        if (errors.isNotEmpty()) {
            return buildFuenteView(fuenteForm)
                .addObject("errors", errors)
        }

        fuenteService.addFuente(
            AddFuenteDTO(
                idTipoFuente = fuenteForm.idTipoFuente!!,
                nombre = fuenteForm.nombre.trim(),
            )
        )

        return ModelAndView("redirect:/fuentes/nuevo?created=true")
    }

    private fun buildEquipoView(equipoForm: CreateEquipoForm): ModelAndView =
        ModelAndView("equipos-create")
            .addObject("equipoForm", equipoForm)

    private fun buildFuenteView(fuenteForm: CreateFuenteForm): ModelAndView =
        ModelAndView("fuentes-create")
            .addObject("fuenteForm", fuenteForm)
            .addObject("tiposFuente", tipoFuenteRepository.findAll(Sort.by("idTipoFuente")))

    private fun validateEquipo(equipoForm: CreateEquipoForm): List<String> {
        val errors = mutableListOf<String>()

        if (equipoForm.nombre.isBlank()) {
            errors.add("El nombre del equipo es obligatorio.")
        }

        if (equipoForm.nombre.trim().length > 50) {
            errors.add("El nombre del equipo no puede exceder 50 caracteres.")
        }

        if (equipoForm.descripcion.trim().length > 255) {
            errors.add("La descripción del equipo no puede exceder 255 caracteres.")
        }

        return errors
    }

    private fun validateFuente(fuenteForm: CreateFuenteForm): List<String> {
        val errors = mutableListOf<String>()

        if (fuenteForm.idTipoFuente == null) {
            errors.add("Debes seleccionar un tipo de fuente.")
        }

        if (fuenteForm.nombre.isBlank()) {
            errors.add("El contenido de la fuente es obligatorio.")
        }

        if (fuenteForm.nombre.trim().length > 255) {
            errors.add("El contenido de la fuente no puede exceder 255 caracteres.")
        }

        return errors
    }
}
