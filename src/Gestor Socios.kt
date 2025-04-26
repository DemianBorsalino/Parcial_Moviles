class GestorSocios : Gestiones<Socio> {
    private val socios = mutableListOf<Socio>()

    override fun alta(item: Socio) {
        socios.add(item)
        println("Socio '${item.nombre}' añadido.")
    }

    override fun baja(id: Int) {
        val s = socios.find { it.id == id }
        if (s != null) {
            socios.remove(s)
            println("Socio '${s.nombre}' eliminado.")
        }
    }

    fun buscar(id: Int): Socio? {
        return socios.find { it.id == id }
    }
}