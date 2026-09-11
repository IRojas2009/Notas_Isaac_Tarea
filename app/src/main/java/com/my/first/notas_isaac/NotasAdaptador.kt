package com.my.first.notas_isaac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.first.notas_isaac.databinding.ItemNotaBinding

class NotasAdaptador(
    private val onUpdateClick: (Nota) -> Unit,
    private val onDeleteClick: (Nota) -> Unit
) : ListAdapter<Nota, NotasAdaptador.NotaViewHolder>(NotaDiffCallback) {

    inner class NotaViewHolder(val binding: ItemNotaBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivActualizar.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onUpdateClick(getItem(position))
                }
            }
            binding.ivEliminar.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(getItem(position))
                }
            }
        }

        fun bind(nota: Nota) {
            binding.itemTitulo.text = nota.titulo
            binding.itemDescripcion.text = nota.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val binding = ItemNotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object NotaDiffCallback : DiffUtil.ItemCallback<Nota>() {
        override fun areItemsTheSame(oldItem: Nota, newItem: Nota): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Nota, newItem: Nota): Boolean =
            oldItem == newItem
    }
}
