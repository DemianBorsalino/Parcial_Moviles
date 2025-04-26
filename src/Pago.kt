import java.time.LocalDate

data class Pago(
    val id: Int,
    val socio: Socio,
    val importe: Double,
    val fecha: LocalDate,
    val concepto: String
)