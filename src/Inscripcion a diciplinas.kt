import Estado
import Socio
import java.time.LocalDate

class GestorInscripciones {
    private val inscripciones = mutableListOf<Inscripcion>()

    fun inscribirSocio(socio: Socio, disciplina: Disciplina) {
        if (socio.tieneCuotasImpagas()) {
            throw Exception("El socio ${socio.nombre} tiene cuotas impagas y no puede inscribirse.")
        }
        if (disciplina.sociosInscriptos.size >= disciplina.capacidadMaxima) {
            throw Exception("No hay cupo disponible en ${disciplina.nombre}.")
        }
        if (socio.estaInscriptoActiva(disciplina)) {
            throw Exception("El socio ya está inscripto activamente en esta disciplina.")
        }

        val nuevaInscripcion = Inscripcion(
            id = generarId(),
            socio = socio,
            disciplina = disciplina,
            fecha = LocalDate.now(),
            EstadoInscripcion = Estado.ACTIVO
        )
        inscripciones.add(nuevaInscripcion)
        socio.listaDeInscripciones.add(nuevaInscripcion)
        disciplina.sociosInscriptos.add(socio)

        println("Socio ${socio.nombre} inscripto en ${disciplina.nombre} exitosamente.")
    }

    fun cancelarInscripcion(inscripcion: Inscripcion) {
        inscripcion.EstadoInscripcion = Estado.CANCELADA
        inscripcion.disciplina.sociosInscriptos.remove(inscripcion.socio)
        println("Inscripción cancelada.")
    }

    fun finalizarInscripcion(inscripcion: Inscripcion) {
        inscripcion.EstadoInscripcion = Estado.FINALIZADA
        inscripcion.disciplina.sociosInscriptos.remove(inscripcion.socio)
        println("Inscripción finalizada.")
    }

    private fun generarId(): Int {
        return inscripciones.size + 1
    }
}