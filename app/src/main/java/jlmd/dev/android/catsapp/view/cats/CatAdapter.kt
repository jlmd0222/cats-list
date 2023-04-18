package jlmd.dev.android.catsapp.view.cats

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jlmd.dev.android.catsapp.R
import jlmd.dev.android.catsapp.databinding.CatViewItemBinding
import jlmd.dev.android.catsapp.view.cats.model.CatItem

class CatAdapter: RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    var items: List<CatItem> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.cat_view_item, parent, false)

        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val user = items[position]

        with(holder){
            binding.catBreed.text = user.breedName
            binding.countryCat.text = user.origin
            binding.intelligenceCat.text = user.intelligence.toString()
        }
    }

    override fun getItemCount() = items.size

    inner class CatViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = CatViewItemBinding.bind(view)
    }
}