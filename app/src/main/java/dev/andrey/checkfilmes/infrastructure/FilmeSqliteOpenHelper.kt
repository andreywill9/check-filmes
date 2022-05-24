package dev.andrey.checkfilmes.infrastructure

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
}