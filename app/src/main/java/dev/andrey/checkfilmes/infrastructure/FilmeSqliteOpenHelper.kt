package dev.andrey.checkfilmes.infrastructure

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getDoubleOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import dev.andrey.checkfilmes.domain.Filme
import java.util.*

private val CREATE_FILME = "create table filme\n" +
        "(\n" +
        "    id             integer\n" +
        "        constraint id_filme\n" +
        "            primary key autoincrement,\n" +
        "    titulo         text not null,\n" +
        "    ano            INTEGER,\n" +
        "    diretor        text,\n" +
        "    nota_imdb      real,\n" +
        "    nota_usuario   real,\n" +
        "    assistido      INT,\n" +
        "    data_assistido integer\n" +
        ")"
private val CREATE_ELENCO = "create table elenco_filme\n" +
        "(\n" +
        "    id       integer\n" +
        "        constraint id_elenco\n" +
        "            primary key autoincrement,\n" +
        "    id_filme INTEGER not null,\n" +
        "    ator     text    not null\n" +
        ")"

class FilmeSqliteOpenHelper(context: Context): SQLiteOpenHelper(context, "filmes.db", null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_FILME)
        db?.execSQL(CREATE_ELENCO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun inserirFilme(filme: Filme) {
        writableDatabase.use {
            val idNovoFilme = it.insert("filme", null, ContentValues().apply {
                put("titulo", filme.titulo)
                put("ano", filme.ano)
                put("diretor", filme.diretor)
                put("nota_imdb", filme.notaImdb)
                put("nota_usuario", filme.notaUsuario)
                put("assistido", if (filme.foiAssistido) 1 else 0)
                put("data_assistido", filme.dataAssistido?.time)
            })
            if (filme.elenco?.isNotEmpty() == true) {
                for (ator in filme.elenco) {
                    it.insert("elenco_filme", null, ContentValues().apply {
                        put("id_filme", idNovoFilme)
                        put("ator", ator)
                    })
                }
            }
        }
    }

    fun buscarTodosFilmes(): List<Filme> {
        readableDatabase.use { database ->
            val listaFilmes = mutableListOf<Filme>()
            database.rawQuery(
                "select * from filme",
                null
            ).use {
                while (it.moveToNext()) {
                    val data = it.getIntOrNull(7)
                    listaFilmes.add(Filme(
                        id = it.getLong(0),
                        titulo = it.getString(1),
                        ano = it.getIntOrNull(2),
                        diretor = it.getStringOrNull(3),
                        notaImdb = it.getDoubleOrNull(4),
                        notaUsuario = it.getDoubleOrNull(5),
                        foiAssistido = it.getInt(6) == 1,
                        dataAssistido = if (data != null) Date(data.toLong()) else null
                    ))
                }
                return listaFilmes
            }
        }
    }
}