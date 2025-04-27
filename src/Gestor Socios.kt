class GestorSocios : Gestiones<Socio>, GeneradorID {
    private val socios = mutableListOf<Socio>()
    private var contameiD = 5;

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

    fun mostrarSocios() {
            if (socios.isEmpty()) {
                println("No hay socios registrados.")
            } else {
                println("\n=== Listado de Socios ===")
                socios.forEach { socio ->
                    println("ID: ${socio.id} | Nombre: ${socio.nombre} | DNI: ${socio.DNI} | Email: ${socio.email ?: "No especificado"}")
                }
            }
        }


    fun mostrarMorosos() {
        val morosos = socios.filter { it.tieneCuotasImpagas() }
        if (morosos.isEmpty()) {
            println("No hay socios morosos.")
        } else {
            println("Socios con cuotas impagas:")
            morosos.forEach {
                println("  ${it.nombre} (DNI: ${it.DNI})")
            }
        }
    }

    fun buscar(id: Int): Socio? {
        return socios.find { it.id == id }
    }

    override fun generoID() : Int {
        contameiD ++
        return contameiD
    }
}