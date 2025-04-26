import tipoDisciplina
data class Disciplina(
    var nombre: String,
    var tipo: tipoDisciplina,
    val id: Int,
    var capacidadMaxima: Int,
    var sociosInscriptos: MutableList<Socio> = mutableListOf(),
    var descuento: Double = 0.0  // nuevo: descuento en porcentaje (ej: 10.0 = 10%)
) {

}