import Socio
import Disciplina
import Inscripcion
import Pago
fun main() {
    val Actividad = Menu()
    Actividad.baseDatos()
    Actividad.menuPrincipal() // */

    /*val gestorSocios = GestorSocios()
    val gestorDisciplinas = GestorDisciplinas()
    val gestorInscripciones = GestorInscripciones()
    val gestorPagos = GestorPagos()

    val socio1 = Socio("Ana López", 12345678, "ana@example.com", id = 1)
    val socio2 = Socio("Bruno Díaz", 87654321, null, id = 2)

    gestorSocios.alta(socio1)
    gestorSocios.alta(socio2)

    // Crear disciplinas
    val futbol = Disciplina("Fútbol", tipoDisciplina.EQUIPO, id = 1, capacidadMaxima = 2)
    val natacion = Disciplina("Natación", tipoDisciplina.INDIVIDUAL, id = 2, capacidadMaxima = 3, descuento = 10.0) // 10% de descuento

    gestorDisciplinas.alta(futbol)
    gestorDisciplinas.alta(natacion)

    // Registrar pagos
    gestorPagos.registrarPago(socio1, 5000.0, "Cuota Fútbol Abril 2024", futbol)
    gestorPagos.registrarPago(socio2, 4000.0, "Cuota Natación Abril 2024", natacion)

    // Inscribir socios (usando try/catch para manejar excepciones)
    try {
        gestorInscripciones.inscribirSocio(socio1, futbol)
        gestorInscripciones.inscribirSocio(socio2, natacion)
    } catch (e: Exception) {
        println("Error al inscribir: ${e.message}")
    }

    // Intentar inscribir de nuevo a mismo socio (forzar error InscripcionDuplicadaException)
    try {
        gestorInscripciones.inscribirSocio(socio1, futbol)
    } catch (e: InscripcionDuplicadaException) {
        println("Error controlado: ${e.message}")
    }

    // Reportes
    println("\n--- Socios inscriptos por disciplina ---")
    gestorDisciplinas.listarSociosPorDisciplina()

    println("\n--- Socios morosos ---")
    gestorSocios.mostrarMorosos()

    println("\n--- Recaudación por disciplina ---")
    gestorPagos.recaudacionPorDisciplina(futbol)
    gestorPagos.recaudacionPorDisciplina(natacion)

    println("\n--- Cantidad de socios por disciplina ---")
    gestorDisciplinas.cantidadDeSociosPorDisciplina()

    println("\n--- Disciplinas con cupo completo ---")
    gestorDisciplinas.disciplinasConCupoCompleto()

    println("\n--- Disciplinas sin inscriptos ---")
    gestorDisciplinas.disciplinasSinInscriptos() // */
}
