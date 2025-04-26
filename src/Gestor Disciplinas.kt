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
        if (d != null && d.sociosInscriptos.isEmpty()) {
            disciplinas.remove(d)
            println("Disciplina '${d.nombre}' eliminada.")
        } else {
            println("No se ha encontrado la disciplina.")
        }
    }

    fun listarSociosPorDisciplina() {
        disciplinas.forEach { disciplina ->
            println("Disciplina: ${disciplina.nombre}")
            if (disciplina.sociosInscriptos.isEmpty()) {
                println("  No hay socios inscriptos.")
            } else {
                disciplina.sociosInscriptos.forEach { socio ->
                    println("  Socio: ${socio.nombre} (DNI: ${socio.DNI})")
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
                is String -> it.nombre.equals(dato, ignoreCase = true) || it.tipo.name.equals(dato, ignoreCase = true)
                is Int -> it.capacidadMaxima == dato
                else -> false
            }
        }
    }
    fun mostrarDisciplinas() {
        disciplinas.forEach {
            println("Disciplina: ${it.nombre}, Tipo: ${it.tipo}, Capacidad: ${it.capacidadMaxima}")
        }
    }

    override fun generoID(): Int {
        idCounter ++
        return idCounter
    }
}