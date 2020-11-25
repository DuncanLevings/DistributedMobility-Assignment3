package sheridan.levings.assignment3.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sheridan.levings.assignment3.R
import sheridan.levings.assignment3.databinding.DataItemBinding
import sheridan.levings.assignment3.domain.Flower

class FlowerListAdapter ( private val onClickListener: OnClickListener): ListAdapter<Flower, FlowerListAdapter.ViewHolder>(FlowerDiffCallback()){

    class ViewHolder private constructor(private val binding: DataItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(count: Int, flower: Flower){
            binding.count.text = binding.root.context.getString(R.string.count, count)
            binding.flower = flower
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flower = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(flower)
        }
        holder.bind(position + 1, flower)
    }

    class FlowerDiffCallback : DiffUtil.ItemCallback<Flower>() {
        override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (flower: Flower) -> Unit) {
        fun onClick(flower: Flower) = clickListener(flower)
    }
}