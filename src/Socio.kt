import Estado
class Socio (
    var nombre: String,
    val DNI: Int,
    var email: String?,
    val id: Int,
    var listaDeInscripciones: MutableList<Inscripcion> = mutableListOf(),
    var pagos: MutableList<Pago> = mutableListOf()
) {
    fun tieneCuotasImpagas(): Boolean {
        val inscripcionesActivas = listaDeInscripciones.filter { it.EstadoInscripcion == Estado.ACTIVO }
        return inscripcionesActivas.any { inscripcion ->
            pagos.none { pago ->
                pago.concepto.contains(inscripcion.disciplina.nombre, ignoreCase = true)
            }
        }
    }

    fun estaInscriptoActiva(disciplina: Disciplina): Boolean {
        return listaDeInscripciones.any { it.disciplina == disciplina && it.EstadoInscripcion == Estado.ACTIVO }
    }
}