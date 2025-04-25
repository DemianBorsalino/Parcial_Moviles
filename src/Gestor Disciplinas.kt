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
            println("No se puede eliminar la disciplina.")
        }
    }

    override fun buscar(id: Int): Disciplina? {
        return disciplinas.find { it.id == id }
    }

    fun mostrarDisciplinas() {
        disciplinas.forEach {
            println("Disciplina: ${it.nombre}, Tipo: ${it.tipo}, Capacidad: ${it.capacidadMaxima}")
        }
    }
}