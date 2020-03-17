package com.example.appnghenhac.Fragment.Personal

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragmnet_dia_nhac.*
import kotlinx.android.synthetic.main.fragmnet_dia_nhac.view.*
import kotlinx.android.synthetic.main.fragmnet_dia_nhac.view.circleView
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class DiaNhacFragment(var baiHat: BaiHat) :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragmnet_dia_nhac, container, false)
        Picasso.with(context).load(baiHat.hinhBaiHat).into(view.circleView)
        Log.d("CrashApp","${baiHat.tenBaiHat}")
//        val objectAnimation=ObjectAnimator.ofFloat(view.circleView,"rotation",0f,360f)
//        objectAnimation.setDuration(10000)
//        objectAnimation.repeatCount=ValueAnimator.INFINITE
//        objectAnimation.repeatMode=ValueAnimator.RESTART
//        objectAnimation.interpolator=LinearInterpolator()
        val startRotateAnimation =
            AnimationUtils.loadAnimation(context, R.anim.android_rotate_animation)
        view.circleView.startAnimation(startRotateAnimation)
        return view
    }
}