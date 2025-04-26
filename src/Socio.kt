class Socio (
    var nombre: String,
    val DNI: Int,
    var email: String?,
    val id: Int,
    var listaDeInscripciones: MutableList<Inscripcion> = mutableListOf(),
    var pagos: MutableList<Pago> = mutableListOf()
) {

}