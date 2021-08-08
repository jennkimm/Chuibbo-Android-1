package com.example.chuibbo_android.camera

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chuibbo_android.R
import com.example.chuibbo_android.background.BackgroundSynthesisFragment
import com.example.chuibbo_android.image.Adapter
import com.example.chuibbo_android.image.ImageViewModel
import kotlinx.android.synthetic.main.download_fragment.view.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.overall_synthesis_confirm_fragment.*
import kotlinx.android.synthetic.main.overall_synthesis_confirm_item.*
import kotlinx.android.synthetic.main.overall_synthesis_confirm_item.view.*

class SynthesisConfirmFragment : Fragment() {

    private lateinit var vm: ImageViewModel
    private lateinit var next_button: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.overall_synthesis_confirm_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar!!.setTitle("사진 편집")

        val ad = Adapter()
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(ImageViewModel::class.java)

        // TODO: 2021/03/29 Recycler View Binding 아닌, ImageView 하나의 Object에 바인딩 하기
        // 리사이클러뷰 적용 보류
//        recycerlview_img.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = ad
//        }

        setFragmentResultListener("requestBitmapKey") { key, bundle ->
            val result = bundle.getParcelable<Bitmap>("bundleKey")
            img_synthesis!!.setImageBitmap(result)
        }

        next_button = ImageButton(context)
        next_button.setImageResource(R.drawable.ic_arrow_right)
        val l3 = androidx.appcompat.widget.Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT,
        )
        next_button.layoutParams = l3
        next_button.setBackground(null)
        activity?.toolbar!!.addView(next_button)
        (next_button.layoutParams as androidx.appcompat.widget.Toolbar.LayoutParams).apply {
            this.gravity = Gravity.RIGHT
        }
        next_button.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, BackgroundSynthesisFragment())
                .commit()
            activity?.toolbar!!.removeView(next_button)
        }

        vm.allItem.observe(viewLifecycleOwner) { ad.addCategoryList(it) }
    }
}
