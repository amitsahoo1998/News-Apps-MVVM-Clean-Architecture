package dev.amits.cleanarchitecturenewsapp.presenter.view.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dev.amits.cleanarchitecturenewsapp.R
import dev.amits.cleanarchitecturenewsapp.databinding.FragmentDetailsBinding
import dev.amits.cleanarchitecturenewsapp.presenter.view.MainActivity
import dev.amits.cleanarchitecturenewsapp.presenter.view.viewmodel.LiveNewsViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    lateinit var viewModel : LiveNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = args.news
        binding.news = news
        val currentTime = Calendar.getInstance().time
        val date = news.publishedAt
        val format  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val localDate = LocalDateTime.parse(date, format)
        val timeInMilliseconds: Long =
            localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
        val hour = TimeUnit.MILLISECONDS.toHours(currentTime.time-timeInMilliseconds)
        val minute = TimeUnit.MILLISECONDS.toMinutes(currentTime.time-timeInMilliseconds)
        if (hour.toInt()==0){
            binding.tvPublishedAt.text = "${minute.toString()} minute ago"
        }else{
            binding.tvPublishedAt.text = "${hour.toString()} hours ago"
        }
        if(news.urlToImage!=null) {
            Glide.with(binding.ivArticleImage.context).load(news.urlToImage)
                .into(binding.ivArticleImage)
        }else{
            binding.ivArticleImage.setImageDrawable(R.drawable.tesla_image.toDrawable())
        }

        binding.shareBtn.setOnClickListener {
            val intent = Intent(android.content.Intent.ACTION_SEND)
            intent.setType("text/plain")
            val shareBody = news.url
            intent.putExtra(Intent.EXTRA_SUBJECT, "See this news")
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, "Share via"))
        }

        binding.saveBtn.setOnClickListener{
            viewModel.saveArticle(news)
            Snackbar.make(view, "News saved successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        (activity as MainActivity).toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).title = args.news.title
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}