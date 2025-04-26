import tipoDisciplina
import Disciplina

class GestorDisciplinas : Gestiones<Disciplina> {
    private val disciplinas = mutableListOf<Disciplina>()

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
}