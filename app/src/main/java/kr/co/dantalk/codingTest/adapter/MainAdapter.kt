package kr.co.dantalk.codingTest.adapter

import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.api.response.AlbumData
import kr.co.dantalk.codingTest.databinding.ItemAlbumBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val datas: ArrayList<AlbumData> = ArrayList()
    var clickEvent: (String, String, ImageView) -> Unit = {_,_,_ -> }

    fun setDatas(arrayList: ArrayList<AlbumData>) {
        datas.clear()
        datas.addAll(arrayList)
        notifyItemRangeInserted(0, datas.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    inner class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AlbumData) {
            binding.root.setOnClickListener {
                clickEvent(data.id, data.imgUrl, binding.imageView)
            }
            Glide.with(binding.root).load(data.imgUrl)
                .transition(withCrossFade())
                .apply(
                    RequestOptions().override(300, 300)
                )
                .into(binding.imageView)
        }
    }
}