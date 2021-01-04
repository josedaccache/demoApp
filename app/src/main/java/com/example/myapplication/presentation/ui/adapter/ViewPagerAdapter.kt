package com.example.myapplication.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R
import com.example.myapplication.models.Image
import com.example.myapplication.utils.getProgressDrawable
import com.example.myapplication.utils.loadImage


class ViewPagerAdapter(
    private var context: Context,
    private var images: ArrayList<Image>
) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image = images[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.layout_image_item, container, false) as ViewGroup
        val ivAlbum: ImageView = layout.findViewById(R.id.ivAlbum)
        val progressDrawable = getProgressDrawable(context)
        ivAlbum.loadImage(image.source, progressDrawable)
        container.addView(layout)
        return layout
    }
}