class Socio (
    val nombre: String,
    val DNI: Int,
    val email: String?,
    val id: Int,
    val listaDeInscripciones: MutableList<Inscripcion> = mutableListOf(),
    val pagos: MutableList<Pago> = mutableListOf()
) {

}