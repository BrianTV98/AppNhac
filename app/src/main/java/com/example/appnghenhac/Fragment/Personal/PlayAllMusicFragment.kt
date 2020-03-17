package com.example.appnghenhac.Fragment.Personal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appnghenhac.Adapter.PlayMusicApdapter
import com.example.appnghenhac.Main.PlayMusicActivity
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R
import kotlinx.android.synthetic.main.activity_play_music.*
import kotlinx.android.synthetic.main.fragme_nghe_tat_ca.*
import kotlinx.android.synthetic.main.fragme_nghe_tat_ca.view.*

class PlayAllMusicFragment(val listData : ArrayList<BaiHat>) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragme_nghe_tat_ca, container,  false)
        Log.d("testPlayAll","ok")

        for(i in listData){
            Log.d("dataPlayAll","${i.tenBaiHat}")
        }
        try{
            view.rcw.adapter=PlayMusicApdapter(context!!,listData)
        }
        catch (e:Exception){
            Log.d("ErrAdapter","Loi}")
        }
        return  view
    }
}