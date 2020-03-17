package com.example.appnghenhac.Adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.appnghenhac.Fragment.Personal.DiaNhacFragment
import com.example.appnghenhac.Fragment.Personal.PlayAllMusicFragment
import com.example.appnghenhac.Model.BaiHat

class ViewPagePlayNhacAdapter(val baiHat: BaiHat,fm: FragmentManager) : FragmentPagerAdapter(fm){
    var fragmentArrayList = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        var fragment : Fragment=DiaNhacFragment(baiHat)
        when(position){
            0-> fragment=fragmentArrayList.get(0)
            1-> {
                Log.d("dachay","da chay vao loag 2")
                fragment= fragmentArrayList.get(1)
            }
        }
        return fragment
  }

    override fun getCount(): Int {
        return  fragmentArrayList.size
    }
    fun addFragement(fragment: Fragment){
        fragmentArrayList.add(fragment)
    }
    fun replay( positon: Int,fragment: Fragment){
        fragmentArrayList.set(positon,fragment)
    }
}