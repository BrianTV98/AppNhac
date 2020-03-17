package com.example.appnghenhac.ViewPageAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.appnghenhac.Fragment.*
import com.example.appnghenhac.Fragment.Personal.PersonalFragment

class ViewPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
    val COUT =5
    override fun getItem(position: Int): Fragment {
        var fragment :Fragment= ZingChatFragment()
        when(position){
            0-> fragment= PersonalFragment()
            1-> fragment= DiscoverFragment()
            2-> fragment= ZingChatFragment()
            3-> fragment= MVFragment()
            4-> fragment= FollowFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return  COUT
    }

}