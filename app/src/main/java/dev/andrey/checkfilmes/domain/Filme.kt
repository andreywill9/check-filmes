package dev.andrey.checkfilmes.domain

import java.util.Date

class Filme(val id: Long, val titulo: String, val ano: Int, val diretor: String,
            val elenco: List<String>, val notaImdb: Double, val notaUsuario: Double,
            val foiAssistido: Boolean, val dataAssistido: Date) { }