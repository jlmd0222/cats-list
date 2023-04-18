package jlmd.dev.android.catsapp.view.cats

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jlmd.dev.android.catsapp.R
import jlmd.dev.android.catsapp.databinding.CatViewItemBinding
import jlmd.dev.android.catsapp.utils.loadFromUrl
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
        val cat = items[position]

        with(holder){
            binding.catBreed.text = cat.breedName
            binding.countryCat.text = cat.origin
            binding.intelligenceCat.text = cat.intelligence.toString()
            //binding.catImage.loadFromUrl(cat.imageUrl)
        }
    }

    override fun getItemCount() = items.size

    inner class CatViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = CatViewItemBinding.bind(view)
    }
}