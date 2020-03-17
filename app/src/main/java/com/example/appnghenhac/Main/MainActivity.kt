package com.example.appnghenhac.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appnghenhac.R
import com.example.appnghenhac.ViewPageAdapter.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitComponent()
    }

    private fun InitComponent() { // gan id
        viewPage.adapter= ViewPageAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPage)// gan cac viewPage tuong ung vs tab layout
        // set icon and lable for tabLayouts
//        tabLayout.getTabAt(0)!!.setIcon(R.drawable.personal).setText("Cá nhân")
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.discover).setText("Khám Phá")
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.zingchat).setText("Zing Chat")
        tabLayout.getTabAt(3)!!.setIcon(R.drawable.mv).setText("MV")
        tabLayout.getTabAt(4)!!.setIcon(R.drawable.follow).setText("Theo dõi")

    }

}
