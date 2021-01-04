package com.example.myapplication.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.interfaces.NewsClickListener
import com.example.myapplication.models.Article
import com.example.myapplication.presentation.ui.adapter.NewsAdapter
import com.example.myapplication.presentation.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), NewsClickListener {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_news, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel =
            ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        observeViewModel()
        newsViewModel.fetchNews()
    }

    private fun observeViewModel() {
        newsViewModel.response.observe(viewLifecycleOwner, { response ->
            response?.let {
                val articles = response.data
                if (articles == null || articles.isEmpty()) {
                    rvNews.visibility = View.GONE
                    tvNoDataFound.visibility = View.VISIBLE
                } else {
                    rvNews.visibility = View.VISIBLE
                    tvNoDataFound.visibility = View.GONE
                    setupNewsList(articles)
                }
            }
        })
        newsViewModel.responseLoadError.observe(viewLifecycleOwner, { isError ->
            isError?.let {
                if (it)
                    showResponse(getString(R.string.label_failure_response))
            }
        })
        newsViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                srlNews.isRefreshing = it
            }
        })
    }

    private fun showResponse(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun setupNewsList(articles: ArrayList<Article>) {
        newsAdapter = NewsAdapter(articles, this)
        rvNews.adapter = newsAdapter

    }

    override fun onNewsClicked(article: Article) {
        newsViewModel.selectedArticle.value = article
        Navigation.findNavController(rootView).navigate(R.id.action_go_to_details)
    }
}