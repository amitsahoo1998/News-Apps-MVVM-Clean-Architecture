package dev.amits.cleanarchitecturenewsapp.presenter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.amits.cleanarchitecturenewsapp.R
import dev.amits.cleanarchitecturenewsapp.data.model.Article
import dev.amits.cleanarchitecturenewsapp.databinding.NewsListItemBinding
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    var type: String? = null

    private val callback=object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,callback)

    inner class MyViewHolder(private val itemBinding : NewsListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {
            if (type.equals("saved")){
                itemBinding.saveBtn.visibility = View.GONE
                itemBinding.fevBtn.visibility = View.VISIBLE
            }else if (type.equals("home")){
                itemBinding.saveBtn.visibility = View.VISIBLE
                itemBinding.fevBtn.visibility = View.GONE
            }
            itemBinding.news = article
            val currentTime = Calendar.getInstance().time
            val date = article.publishedAt
            val format  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val localDate = LocalDateTime.parse(date, format)
            val timeInMilliseconds: Long =
                localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
            val hour = TimeUnit.MILLISECONDS.toHours(currentTime.time-timeInMilliseconds).toString()
            itemBinding.tvPublishedAt.text = "$hour hours ago"
            if(article.urlToImage!=null) {
                Glide.with(itemBinding.ivArticleImage.context).load(article.urlToImage)
                    .into(itemBinding.ivArticleImage)
            }else{
                itemBinding.ivArticleImage.setImageDrawable(R.drawable.tesla_image.toDrawable())
            }
            itemBinding.root.setOnClickListener {
                itemClickListener?.let {
                    it(article)
                }
            }
            itemBinding.saveBtn.setOnClickListener{
                newsSaveListener?.let {
                    it(article)
                }
            }
            itemBinding.fevBtn.setOnClickListener {
                newsDeleteListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : NewsListItemBinding = NewsListItemBinding.inflate(layoutInflater , parent ,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var itemClickListener: ((Article) -> Unit)? =null

    fun setOnclickItemListener(listener:(Article)->Unit){
        itemClickListener=listener
    }

    private var newsSaveListener : ((Article) -> Unit)? = null

    fun setSaveBtnClickListener(listener: (Article) -> Unit){
        newsSaveListener = listener
    }

    private var newsDeleteListener : ((Article) -> Unit)? = null

    fun setFevBtnClickListener(listener: (Article) -> Unit){
        newsDeleteListener = listener
    }
}