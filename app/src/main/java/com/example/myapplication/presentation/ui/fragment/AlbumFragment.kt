package com.example.myapplication.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Image
import com.example.myapplication.presentation.ui.adapter.ViewPagerAdapter
import com.example.myapplication.presentation.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_slideshow.*


class AlbumFragment : Fragment() {

    private lateinit var albumViewModel: AlbumViewModel
    private var mViewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slideshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        observeViewModel()
        albumViewModel.fetchImage()
    }

    private fun observeViewModel() {
        albumViewModel.response.observe(viewLifecycleOwner, { response ->
            response?.let {
                val images = response.imageData
                images?.let {
                    setupViews(it)
                }
            }
        })
        albumViewModel.responseLoadError.observe(viewLifecycleOwner, { isError ->
            isError?.let {
                if (it)
                    showResponse(getString(R.string.label_failure_response))
            }
        })
        albumViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                pbLoading.visibility = if(it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun setupViews(images: ArrayList<Image>) {
        mViewPagerAdapter = ViewPagerAdapter(requireContext(), images)
        viewPager.adapter = mViewPagerAdapter

        dotsIndicator.setViewPager(viewPager)
        viewPager.adapter?.registerDataSetObserver(dotsIndicator.dataSetObserver)
    }

    private fun showResponse(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}