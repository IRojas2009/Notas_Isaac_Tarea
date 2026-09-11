package com.my.first.notas_isaac

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.first.notas_isaac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NotasDatabaseHelper
    private lateinit var notasAdaptador: NotasAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotasDatabaseHelper(this)

        notasAdaptador = NotasAdaptador(
            onUpdateClick = { nota ->
                val intent = Intent(this, ActualizarNotaActivity::class.java).apply {
                    putExtra("id_nota", nota.id)
                }
                startActivity(intent)
            },
            onDeleteClick = { nota ->
                db.deleteNota(nota.id)
                notasAdaptador.submitList(db.getAllNotas())
                Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show()
            }
        )
        notasAdaptador.submitList(db.getAllNotas())
        binding.notasRv.layoutManager = LinearLayoutManager(this)
        binding.notasRv.adapter = notasAdaptador
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(this, AgregarNotaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        notasAdaptador.submitList(db.getAllNotas())
    }
}
