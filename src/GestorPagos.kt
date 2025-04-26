import java.time.LocalDate

class GestorPagos {
    private val pagos = mutableListOf<Pago>()

    fun registrarPago(socio: Socio, importe: Double, concepto: String) {
        val nuevoPago = Pago(
            id = generarId(),
            socio = socio,
            importe = importe,
            fecha = LocalDate.now(),
            concepto = concepto
        )
        pagos.add(nuevoPago)
        socio.pagos.add(nuevoPago)

        println("Pago registrado para ${socio.nombre}: $$importe - $concepto")
    }

    fun consultarPagosPorSocio(socio: Socio): List<Pago> {
        return pagos.filter { it.socio == socio }
    }

    fun consultarPagosPorDisciplina(disciplina: Disciplina): List<Pago> {
        return pagos.filter { pago ->
            pago.socio.listaDeInscripciones.any { it.disciplina == disciplina }
        }
    }

    private fun generarId(): Int {
        return pagos.size + 1
    }
}