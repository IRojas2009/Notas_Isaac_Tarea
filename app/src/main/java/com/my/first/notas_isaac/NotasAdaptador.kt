package com.my.first.notas_isaac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.my.first.notas_isaac.databinding.ItemNotaBinding

class NotasAdaptador(
    private var notas: List<Nota>
) : RecyclerView.Adapter<NotasAdaptador.NotaViewHolder>() {

    class NotaViewHolder(val binding: ItemNotaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val binding = ItemNotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotaViewHolder(binding)
    }

    override fun getItemCount(): Int = notas.size

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.binding.itemTitulo.text = nota.titulo
        holder.binding.itemDescripcion.text = nota.descripcion
    }

    fun refreshData(newNotas: List<Nota>) {
        notas = newNotas
        notifyDataSetChanged()
    }
}
