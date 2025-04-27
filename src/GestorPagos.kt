import java.time.LocalDate

class GestorPagos {
    private val pagos = mutableListOf<Pago>()

    fun registrarPago(socio: Socio, importeOriginal: Double, concepto: String, disciplina: Disciplina) {
        val descuentoAplicado = importeOriginal * (disciplina.descuento / 100)
        val importeFinal = importeOriginal - descuentoAplicado

        val nuevoPago = Pago(
            id = generarId(),
            socio = socio,
            importe = importeFinal,
            fecha = LocalDate.now(),
            concepto = concepto
        )
        pagos.add(nuevoPago)
        socio.pagos.add(nuevoPago)

        println("Pago registrado para ${socio.nombre}: $$importeFinal (${disciplina.descuento}% de descuento aplicado) - $concepto")
    }

    fun consultarPagosPorSocio(socio: Socio): List<Pago> {
        return pagos.filter { it.socio == socio }
    }

    fun consultarPagosPorDisciplina(disciplina: Disciplina): List<Pago> {
        return pagos.filter { pago ->
            pago.socio.listaDeInscripciones.any { it.disciplina == disciplina }
        }
    }

    fun recaudacionPorDisciplina(disciplina: Disciplina): Double {
        val pagosDeLaDisciplina = consultarPagosPorDisciplina(disciplina)
        val total = pagosDeLaDisciplina.sumOf { it.importe }
        println("Recaudación total para ${disciplina.nombre}: $$total")
        return total
    }

    private fun generarId(): Int {
        return  + 1
    }
    fun consultarTodosLosPagos(): List<Pago> {
        return pagos
    }
}