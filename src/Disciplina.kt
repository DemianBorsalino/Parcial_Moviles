import tipoDisciplina
data class Disciplina(
    var nombre: String,
    var tipo: tipoDisciplina,
    val id: Int,
    var capacidadMaxima: Int,
    var sociosInscriptos: MutableList<Socio> = mutableListOf()
) {

}