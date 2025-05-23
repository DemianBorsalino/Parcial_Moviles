    class Menu {
        val gestorSocios = GestorSocios()
        val gestorDisciplinas = GestorDisciplinas()
        val gestorInscripciones = GestorInscripciones()
        val gestorPagos = GestorPagos()

        fun baseDatos() {
            // Crear socios
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
            gestorDisciplinas.disciplinasSinInscriptos()

        }

        fun menuPrincipal() {
            var opcion: Int?

            do {
                println("\nBienvenido al sistema de gestión")
                println("¿Qué desea gestionar?:")
                println("1. Disciplinas")
                println("2. Socios")
                println("3. Pagos")
                println("4. Inscripciones")
                println("0. Salir")

                opcion = readLine()?.toIntOrNull()

                when (opcion) {
                    1 -> menuDisciplinas()
                    2 -> menuSocios()
                    3 -> menuPagos()
                    4 -> menuInscripciones()
                    0 -> println("Adios! Vuelva pronto!")
                    else -> println("Opción inválida")
                }
            } while (opcion != 0)
        }

        fun menuDisciplinas() {
            println("=== Gestión de Disciplinas ===")
            println("Ahora, eliga una opcion:")
            println("1. Agregar disciplina")
            println("2. Eliminar disciplina")
            println("3. Buscar disciplina")
            println("4. Mostrar disciplinas")
            /*println("5. Lista de socios por Disciplina")
            println("6. Cantidad de Socias Por Disciplina")
            println("7. Disciplinas Completas")
            println("8. Disciplinas sin inscriptos")
           */
            println("0. Salir")

            when (readLine()?.toIntOrNull()) {
                1 -> agregarDisciplina()
                2 -> eliminarDisciplina()
                3 -> buscarDisciplina()
                4 -> gestorDisciplinas.mostrarDisciplinas()
               /* 5 -> gestorDisciplinas.listarSociosPorDisciplina()
                6 -> gestorDisciplinas.cantidadDeSociosPorDisciplina()
                7 -> gestorDisciplinas.disciplinasConCupoCompleto()
                8 -> gestorDisciplinas.disciplinasSinInscriptos()
                */
                0 -> println("Adios! Vuelva pronto!")
                else -> println("Opción inválida")
            }
        }

        private fun agregarDisciplina() {
            println("Ingrese nombre de la disciplina:")
            val nombre = readLine() ?: return

            var tipo: tipoDisciplina? = null
            do {
                println("Ingrese tipo de disciplina (EQUIPO o INDIVIDUAL):")
                val tipoInput = readLine()

                tipo = try {
                    tipoInput?.let { tipoDisciplina.valueOf(it.uppercase()) }
                } catch (e: IllegalArgumentException) {
                    println("Tipo inválido. Intente de nuevo.")
                    null
                }
            } while (tipo == null)


            println("Ingrese capacidad máxima:")
            val capacidad = readLine()?.toIntOrNull() ?: return

            val nuevaDisciplina = Disciplina(
                nombre = nombre,
                tipo = tipo,
                id = gestorDisciplinas.generoID(),
                capacidadMaxima = capacidad
            )

            gestorDisciplinas.alta(nuevaDisciplina)
        }

        private fun eliminarDisciplina() {
            do {
                val id = getInputIntOrExit("Ingrese el ID de la Disciplina que desea eliminar (o 0 para volver atrás):")

                if (id == null) {
                    println("Volviendo al menú...")
                    return
                }

                try {
                    gestorDisciplinas.baja(id)
                    break // Si se elimina correctamente, salimos del bucle
                } catch (e: IdInvalidoException) {
                    println(e.message)
                    // sigue el bucle y vuelve a pedir
                }
            } while (true)
        }

        private fun buscarDisciplina() {
            println("Ingrese dato para buscar:")
            val dato = readLine() ?: return

            val numero = dato.toIntOrNull()

            val resultado = if (numero != null) {
                gestorDisciplinas.buscarDisciplina(numero)
            } else if (dato.equals("EQUIPO", ignoreCase = true) || dato.equals("INDIVIDUAL", ignoreCase = true)) {
                gestorDisciplinas.buscarDisciplina(tipoDisciplina.valueOf(dato.uppercase()))
            } else {
                gestorDisciplinas.buscarDisciplina(dato)
            }

            if (resultado != null) {
                println("Disciplina encontrada: ${resultado.nombre}, ${resultado.id}")
            } else {
                println("No se encontró disciplina.")
            }


        }




        fun menuSocios() {
            println("=== Gestión de Socios ===")
            println("Seleccione una opción:")
            println("1. Inscribir socio")
            println("2. Eliminar inscripción de socio")
            println("3. Buscar socio por id")
            println("4. Ver socios inscriptos en una disciplina")
            println("5. listado de socios")
            println("0. Volver")

            when (readLine()?.toIntOrNull()) {
                1 -> darDeAltaSocio()
                2 -> darDeBajaSocio()
                3 -> buscarSocio()
                4 -> gestorSocios.mostrarMorosos()
                5 -> gestorSocios.mostrarSocios()
                0 -> println("Volviendo al menú principal...")
                else -> println("Opción inválida")
            }
        }

        private fun darDeAltaSocio() {
            println("Ingrese nombre del socio:")
            val nombre = readLine() ?: return

            println("Ingrese DNI:")
            val dni = readLine()?.toIntOrNull() ?: run {
                println("DNI inválido.")
                return
            }

            println("Ingrese email (opcional, presione Enter si no quiere ponerlo):")
            val emailInput = readLine()
            val email = if (emailInput.isNullOrBlank()) null else emailInput

            val nuevoSocio = Socio(
                nombre = nombre,
                DNI = dni,
                email = email,
                id = gestorSocios.generoID()
            )

            gestorSocios.alta(nuevoSocio)
        }

        private fun darDeBajaSocio() {
            println("Ingrese el ID del socio que desea eliminar:")
            val id = readLine()?.toIntOrNull() ?: run {
                println("ID inválido.")
                return
            }

            val socio = gestorSocios.buscar(id)
            if (socio != null) {
                gestorSocios.baja(id)
            } else {
                println("No se encontró un socio con ese ID.")
            }
        }

        private fun buscarSocio() {
            println("Ingrese el ID del socio que desea buscar:")
            val id = readLine()?.toIntOrNull()

            if (id == null) {
                println("ID inválido. Debe ingresar un número.")
                return
            }

            val socio = gestorSocios.buscar(id)

            if (socio != null) {
                println("\n=== Socio encontrado ===")
                println("ID: ${socio.id}")
                println("Nombre: ${socio.nombre}")
                println("DNI: ${socio.DNI}")
                println("Email: ${socio.email ?: "No especificado"}")
                println("Cantidad de inscripciones: ${socio.listaDeInscripciones.size}")
                println("Cantidad de pagos: ${socio.pagos.size}")
            } else {
                println("No se encontró un socio con el ID ingresado.")
            }
        }


        fun menuPagos() {
            println("=== Gestión de Pagos ===")
            println("Ahora, elige una opción:")
            println("1. Registrar nuevo pago")
            println("2. Consultar pagos de un socio")
            println("3. Consultar recaudación por disciplina")
            println("4. Mostrar todos los pagos")
            println("0. Salir")

            when (readLine()?.toIntOrNull()) {
                1 -> registrarNuevoPago()
                2 -> consultarPagosDeSocio()
                3 -> consultarRecaudacionPorDisciplina()
                4 -> mostrarTodosLosPagos()
                0 -> println("Adiós! Volviendo al menú principal.")
                else -> println("Opción inválida")
            }
        }

        private fun registrarNuevoPago() {
            println("Ingrese el ID del socio:")
            val idSocio = readLine()?.toIntOrNull() ?: run {
                println("ID inválido.")
                return
            }

            val socio = gestorSocios.buscar(idSocio)
            if (socio == null) {
                println("Socio no encontrado.")
                return
            }

            println("Ingrese el importe original:")
            val importeOriginal = readLine()?.toDoubleOrNull() ?: run {
                println("Importe inválido.")
                return
            }

            println("Ingrese el concepto del pago:")
            val concepto = readLine() ?: return

            println("Ingrese el Nombre de la disciplina:")
            val nombreDisciplina = readLine()?: run {
                println("Nombre de disciplina inválido.")
                return
            }

            val disciplina = gestorDisciplinas.buscarDisciplina(nombreDisciplina)
            if (disciplina == null) {
                println("Disciplina no encontrada.")
                return
            }

            /*val disciplina = gestorDisciplinas.buscarDisciplinaPorId(3)
            if (disciplina != null) {
                println("Disciplina encontrada: ${disciplina.nombre}")
            } else {
                println("No se encontró la disciplina con ese ID.")
            }*/


            gestorPagos.registrarPago(socio, importeOriginal, concepto, disciplina)
        }

        private fun consultarPagosDeSocio() {
            println("Ingrese el ID del socio:")
            val idSocio = readLine()?.toIntOrNull() ?: run {
                println("ID inválido.")
                return
            }

            val socio = gestorSocios.buscar(idSocio)
            if (socio == null) {
                println("Socio no encontrado.")
                return
            }

            val pagos = gestorPagos.consultarPagosPorSocio(socio)
            if (pagos.isEmpty()) {
                println("El socio no tiene pagos registrados.")
            } else {
                println("Pagos de ${socio.nombre}:")
                pagos.forEach { pago ->
                    println("ID: ${pago.id}, Importe: $${pago.importe}, Fecha: ${pago.fecha}, Concepto: ${pago.concepto}")
                }
            }
        }

        private fun consultarRecaudacionPorDisciplina() {
            println("Ingrese el Nombre de la disciplina:")
            val nombreDisciplina = readLine()?: run {
                println("Nombre de disciplina inválido.")
                return
            }

            val disciplina = gestorDisciplinas.buscarDisciplina(nombreDisciplina)
            if (disciplina == null) {
                println("Disciplina no encontrada.")
                return
            }

            gestorPagos.recaudacionPorDisciplina(disciplina)
        }
        private fun mostrarTodosLosPagos() {
            val todosLosPagos = gestorPagos.consultarTodosLosPagos()
            if (todosLosPagos.isEmpty()) {
                println("No hay pagos registrados.")
            } else {
                println("Listado de todos los pagos:")
                todosLosPagos.forEach { pago ->
                    println("ID: ${pago.id}, Socio: ${pago.socio.nombre}, Importe: $${pago.importe}, Fecha: ${pago.fecha}, Concepto: ${pago.concepto}")
                }
            }
        }

        fun menuInscripciones() {
            println("=== Gestión de Inscripciones ===")
            println("Seleccione una opción:")
            println("1. Inscribir socio a disciplina")
            println("2. Eliminar inscripción de socio")
            println("3. Ver socios inscriptos en una disciplina")
            println("0. Volver")

            when (readLine()?.toIntOrNull()) {
                1 -> inscribirSocioADisciplina()
                2 -> eliminarInscripcion()
                3 -> verSociosInscriptos()
                0 -> println("Volviendo al menú principal...")
                else -> println("Opción inválida")
            }
        }
        private fun inscribirSocioADisciplina() {
            println("Ingrese el ID del socio:")
            val socioId = readLine()?.toIntOrNull() ?: return

            println("Ingrese el ID de la disciplina:")
            val disciplinaId = readLine()?.toIntOrNull() ?: return

            val socio = gestorSocios.buscar(socioId)
            val disciplina = gestorDisciplinas.buscarDisciplinaPorId(disciplinaId)

            if (socio == null || disciplina == null) {
                println("Socio o Disciplina no encontrados.")
                return
            }

            try {
                gestorInscripciones.inscribirSocio(socio, disciplina)
                //println("Socio ${socio.nombre} inscripto en ${disciplina.nombre} correctamente.")
            } catch (e: Exception) {
                println("Error al inscribir: ${e.message}")
            }
        }
        private fun eliminarInscripcion() {
            println("Ingrese el ID del socio:")
            val socioId = readLine()?.toIntOrNull() ?: return

            println("Ingrese el ID de la disciplina:")
            val disciplinaId = readLine()?.toIntOrNull() ?: return

            val socio = gestorSocios.buscar(socioId)
            val disciplina = gestorDisciplinas.buscarDisciplinaPorId(disciplinaId)

            if (socio == null || disciplina == null) {
                println("Socio o Disciplina no encontrados.")
                return
            }

            val inscripcion = socio.listaDeInscripciones.find {
                it.disciplina.id == disciplina.id && it.EstadoInscripcion == Estado.ACTIVA
            }

            if (inscripcion == null) {
                println("El socio no tiene una inscripción activa en esta disciplina.")
                return
            }

            gestorInscripciones.cancelarInscripcion(inscripcion)
            println("Inscripción cancelada correctamente.")
        }


        private fun verSociosInscriptos() {
            println("Ingrese el ID de la disciplina:")
            val disciplinaId = readLine()?.toIntOrNull() ?: return

            val disciplina = gestorDisciplinas.buscarDisciplinaPorId(disciplinaId)

            if (disciplina == null) {
                println("Disciplina no encontrada.")
                return
            }

            println("Socios inscriptos en ${disciplina.nombre}:")
            if (disciplina.sociosInscriptos.isEmpty()) {
                println("No hay socios inscriptos.")
            } else {
                disciplina.sociosInscriptos.forEach { socio ->
                    println("- ${socio.nombre} (DNI: ${socio.DNI})")
                }
            }
        }
        fun getInputIntOrExit(message: String): Int? {
            println(message)
            val input = readLine()

            val valor = input?.toIntOrNull()

            if (valor == null) {
                println("Entrada no válida. Por favor ingrese un número.")
                return getInputIntOrExit(message)
            }

            if (valor == 0) {
                return null // El usuario eligió salir
            }

            return valor
        }


}
