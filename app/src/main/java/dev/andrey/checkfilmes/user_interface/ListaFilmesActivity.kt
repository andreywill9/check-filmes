package dev.andrey.checkfilmes.user_interface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.andrey.checkfilmes.R
import dev.andrey.checkfilmes.databinding.ActivityCadastroFilmeBinding
import dev.andrey.checkfilmes.databinding.ActivityListaFilmesBinding

class ListaFilmesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAbrirCadastro.setOnClickListener {
            startActivity(Intent(applicationContext, CadastroFilmeActivity::class.java))
        }
    }
}