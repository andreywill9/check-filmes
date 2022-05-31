package dev.andrey.checkfilmes.domain

import java.util.Date

class Filme(val id: Long? = null, val titulo: String, val ano: Int? = null, val diretor: String? = null,
            val elenco: List<String>? = null, val notaImdb: Double? = null, val notaUsuario: Double? = null,
            val foiAssistido: Boolean = false, val dataAssistido: Date? = null)