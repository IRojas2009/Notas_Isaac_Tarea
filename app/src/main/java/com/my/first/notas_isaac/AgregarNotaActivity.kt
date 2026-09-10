package com.my.first.notas_isaac

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.my.first.notas_isaac.databinding.ActivityAgregarNotaBinding

class AgregarNotaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAgregarNotaBinding
    private lateinit var db : NotasDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotasDatabaseHelper(this)

        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty()){
                guardarNota(titulo,descripcion)
            }else {
                Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarNota(titulo: String, descripcion: String) {
        val nota = Nota(0, titulo, descripcion)
        db.insertNota(nota)
        finish()
        Toast.makeText(this, "Se ha agregado la nota con éxito!", Toast.LENGTH_SHORT).show()
    }
}