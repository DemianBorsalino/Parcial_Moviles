import java.time.LocalDate
import Disciplina
import Estado
class Inscripcion(
    val id: Int,
    val socio: Socio, // Esto implica que Socio debe estar declarado antes o como referencia cruzada
    val disciplina: Disciplina,
    val fecha: LocalDate,
    var EstadoInscripcion: Estado
) {

}