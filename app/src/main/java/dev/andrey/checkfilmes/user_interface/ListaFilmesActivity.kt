package dev.andrey.checkfilmes.user_interface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.andrey.checkfilmes.R

class ListaFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)
    }
}