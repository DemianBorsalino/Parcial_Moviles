import tipoDisciplina
import Disciplina
class GestorDisciplinas : Gestiones<Disciplina> , GeneradorID{
    private val disciplinas = mutableListOf<Disciplina>()
    private var idCounter: Int = 10

    override fun alta(item: Disciplina) {
        disciplinas.add(item)

        println("Disciplina '${item.nombre}' añadida.")
    }

    override fun baja(id: Int) {
        val d = disciplinas.find { it.id == id }

        if (d == null) {
            throw IdInvalidoException("No se encontró la disciplina con el ID ingresado.")
        }

        /*if (d.sociosInscriptos.isNotEmpty()) {
            throw IdInvalidoException("No se puede eliminar: la disciplina '${d.nombre}' tiene socios inscriptos.")
        }*/

        disciplinas.remove(d)
        println("Disciplina '${d.nombre}' eliminada exitosamente.")
    }



    fun listarSociosPorDisciplina() {
        disciplinas.forEach { disciplina ->
            println("Disciplina: ${disciplina.nombre}")
            if (disciplina.sociosInscriptos.isEmpty()) {
                println("  No hay socios inscriptos.")
            } else {
                disciplina.sociosInscriptos.forEach { socio ->
                    println("  Socio: ${socio.nombre} (DNI: ${socio.DNI}) \n ID: ${socio.id}")
                }
            }
        }
    }

    fun cantidadDeSociosPorDisciplina() {
        disciplinas.forEach { disciplina ->
            println("Disciplina: ${disciplina.nombre} - Socios inscriptos: ${disciplina.sociosInscriptos.size}/${disciplina.capacidadMaxima}")
        }
    }

    fun disciplinasConCupoCompleto() {
        val completas = disciplinas.filter { it.sociosInscriptos.size >= it.capacidadMaxima }
        if (completas.isEmpty()) {
            println("No hay disciplinas con cupo completo.")
        } else {
            println("Disciplinas con cupo completo:")
            completas.forEach {
                println("  ${it.nombre}")
            }
        }
    }

    fun disciplinasSinInscriptos() {
        val vacias = disciplinas.filter { it.sociosInscriptos.isEmpty() }
        if (vacias.isEmpty()) {
            println("Todas las disciplinas tienen al menos un socio.")
        } else {
            println("Disciplinas sin inscriptos:")
            vacias.forEach {
                println("  ${it.nombre}")
            }
        }
    }

    fun buscarDisciplina(dato: Any): Disciplina? {
        return disciplinas.find {
            when (dato) {
                is String -> it.nombre.equals(dato, ignoreCase = true)
                is Int -> it.capacidadMaxima == dato
                is tipoDisciplina -> it.tipo == dato
                else -> false
            }
        }
    }
    fun mostrarDisciplinas() {
        disciplinas.forEach {
            println("ID:${it.id} ,Disciplina: ${it.nombre}, Tipo: ${it.tipo}, Capacidad: ${it.capacidadMaxima}")
        }
    }

    override fun generoID(): Int {
        idCounter ++
        return idCounter
    }
    fun buscarDisciplinaPorId(id: Int): Disciplina? {
        return disciplinas.find { it.id == id }
    }
}