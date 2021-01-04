package com.example.myapplication.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.myapplication.R
import com.example.myapplication.presentation.viewmodel.NewsViewModel
import com.example.myapplication.utils.getNewsFormattedDate
import com.example.myapplication.utils.getProgressDrawable
import com.example.myapplication.utils.loadImage
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var progressDrawable: CircularProgressDrawable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel =
            ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        progressDrawable = getProgressDrawable(requireContext())
        setupViews()
    }

    private fun setupViews() {
        newsViewModel.selectedArticle.value?.let { article ->
            tvTitle.text = article.title
            tvDate.text = getNewsFormattedDate(article.publishedAt)
            ivImage.loadImage(article.urlToImage, progressDrawable)
            tvContent.text = article.content
        }
    }
}