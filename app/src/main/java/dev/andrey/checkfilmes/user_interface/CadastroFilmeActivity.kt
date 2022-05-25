package dev.andrey.checkfilmes.user_interface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.andrey.checkfilmes.R
import dev.andrey.checkfilmes.databinding.ActivityCadastroFilmeBinding

class CadastroFilmeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNovoFilme.setOnClickListener {
            if (!validarCampos()) return@setOnClickListener
            cadastrarFilme()
        }
    }

    private fun validarCampos(): Boolean {
        if (binding.edtNomeFilme.text.isNullOrEmpty()) {
            Toast.makeText(this, "Informe o nome do filme", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.switchJaAssisti.isActivated && (binding.edtData.text.isNullOrEmpty())) {
            Toast.makeText(this, "Informe quando assistiu o filme", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun cadastrarFilme() {

    }
}