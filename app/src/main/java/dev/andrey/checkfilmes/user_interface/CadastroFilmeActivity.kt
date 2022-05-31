package dev.andrey.checkfilmes.user_interface

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.andrey.checkfilmes.databinding.ActivityCadastroFilmeBinding
import dev.andrey.checkfilmes.domain.Filme
import dev.andrey.checkfilmes.infrastructure.FilmeSqliteOpenHelper
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CadastroFilmeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroFilmeBinding

    private lateinit var db: FilmeSqliteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FilmeSqliteOpenHelper(this)
        binding = ActivityCadastroFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNovoFilme.setOnClickListener {
            if (!validarCampos()) return@setOnClickListener
            cadastrarFilme()
        }

        binding.switchJaAssisti.setOnCheckedChangeListener {_, check ->
            run {
                binding.containerReproducao.visibility = if (check) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }

    private fun validarCampos(): Boolean {
        if (binding.edtNomeFilme.text.isNullOrEmpty()) {
            Toast.makeText(this, "Informe o nome do filme", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.switchJaAssisti.isChecked && (binding.edtData.text.isNullOrEmpty())) {
            Toast.makeText(this, "Informe quando assistiu o filme", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun cadastrarFilme() {
        val filme = Filme(titulo = binding.edtNomeFilme.text.toString(),
            foiAssistido = binding.switchJaAssisti.isChecked,
            notaUsuario = binding.notaUsuario.rating.toDouble(),
            dataAssistido = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(binding.edtData.text.toString()))
        try {
            db.inserirFilme(filme)
            Toast.makeText(this, "Filme inserido com sucesso", Toast.LENGTH_LONG).show()
            finish()
        } catch (erro: Exception) {
            Toast.makeText(this, "Não foi possível inserir o filme", Toast.LENGTH_LONG).show()
        }
    }
}