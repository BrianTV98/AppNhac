package com.example.appnghenhac.Main

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import com.example.appnghenhac.Adapter.ViewPagePlayNhacAdapter
import com.example.appnghenhac.Fragment.Personal.DiaNhacFragment
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_danh_sach_the_loai.*
import kotlinx.android.synthetic.main.activity_play_music.*
import kotlinx.android.synthetic.main.fragmnet_dia_nhac.*
import java.lang.Exception
import java.text.SimpleDateFormat
import androidx.core.view.ViewCompat.setX
import android.opengl.ETC1.getWidth

import android.view.View
import androidx.core.net.toUri
import com.example.appnghenhac.Fragment.Personal.PlayAllMusicFragment
import kotlinx.android.synthetic.main.banner_row.*
import kotlinx.android.synthetic.main.fragmnet_dia_nhac.view.*
import java.net.URI
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList



class PlayMusicActivity : AppCompatActivity(){
    var  media: MediaPlayer= MediaPlayer()
    var mp3= PlayMp3()
    var handler=Handler()
    var arrBaiHat= ArrayList<BaiHat>()
    var repeat =false
    var random=true
    var position: Int=0
    var threadHandler= Handler()
    lateinit var adapternhac:ViewPagePlayNhacAdapter
    lateinit var diaNhacFragment: DiaNhacFragment

    override fun onPause() {
        super.onPause()
        media.stop()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)
        imgShuffe.setImageResource(R.drawable.iconshuffled)
        init()
            val baiHat: BaiHat?= intent.extras?.getParcelable("BaiHat")
            val ArrbaiHat: ArrayList <BaiHat>?= intent.extras?.getParcelableArrayList("ArrayBaiHat")
            if (baiHat!=null) {
//                Toast.makeText(this," bai hat ${baiHat?.tenBaiHat}",Toast.LENGTH_LONG).show()
                arrBaiHat.clear()
                arrBaiHat.add(baiHat)

                adapternhac= ViewPagePlayNhacAdapter(arrBaiHat.get(position),supportFragmentManager)
                diaNhacFragment=DiaNhacFragment(arrBaiHat.get(position))
                adapternhac.addFragement(diaNhacFragment)
//                adapternhac.addFragement(PlayAllMusicFragment(arrBaiHat))
                viewPagePlayMusic.adapter= adapternhac

                toolBarPlayMusic?.setTitle(baiHat.tenBaiHat) // set lai tieu de cho bai nhac
                mp3.execute(baiHat.linkBaiHat) //chay bai hat
                threadHandler.postDelayed(UpdateSeekBarThread(),50)

                //test Servce

            }
            else{

                if(ArrbaiHat!=null){
                    arrBaiHat.clear()
                    for(i in ArrbaiHat){
                        arrBaiHat.add(i)
                    }
                    Log.d("okoko","${arrBaiHat.get(0).tenBaiHat}")

                    adapternhac= ViewPagePlayNhacAdapter(arrBaiHat.get(0),supportFragmentManager)
                    diaNhacFragment=DiaNhacFragment(arrBaiHat.get(0))
                    adapternhac.addFragement(diaNhacFragment)
                    adapternhac.addFragement(PlayAllMusicFragment(arrBaiHat))
                    toolBarPlayMusic?.setTitle(arrBaiHat[position].tenBaiHat)
                    viewPagePlayMusic.adapter= adapternhac

//                    mp3.execute(arrBaiHat.get(0).linkBaiHat) //chay bai hat
                    //test kieu moi
                    media = MediaPlayer.create(this,arrBaiHat.get(0).linkBaiHat!!.toUri())
                    media.start()
                    TimeSong()
                    threadHandler.postDelayed(UpdateSeekBarThread(),50) // gan bai nhacj vs seekbar

                }
                else{
                    Toast.makeText(this," EROR ",Toast.LENGTH_LONG).show()
                }
            }
//        }
//
        imgplay.setOnClickListener { // bat su kien nut play
           if(media.isPlaying==true){
               media.pause()
               imgplay.setImageResource(R.drawable.iconplay)
           }else{
              media.start()
               imgplay.setImageResource(R.drawable.iconpause)
           }

        }
        imgrepeat.setOnClickListener {

            Log.d("repeat","$repeat")
            if(repeat==false){
                Toast.makeText(this, "Lặp lại",Toast.LENGTH_SHORT).show()
                if(random==true){
                    random=false
                    media?.setOnCompletionListener {
                        nextPlay()
                        TimeSong()
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned)
                    imgShuffe.setImageResource(R.drawable.iconshuffled)
                }
                imgrepeat.setImageResource(R.drawable.iconsyned)
                repeat=true
            }
            else {
                imgrepeat.setImageResource(R.drawable.iconrepeat)
                media?.setOnCompletionListener {
                    position++
                    nextPlay()
                    TimeSong()
                }
                repeat=false
            }
        }
        imgShuffe.setOnClickListener {
            if(random==false){
                Toast.makeText(this, "Xáo trôn",Toast.LENGTH_SHORT).show()
                if(repeat==true){
                    random=true
                    repeat=false
                    Collections.shuffle(arrBaiHat)
                    media?.setOnCompletionListener {
                        position++
                        nextPlay()
                        TimeSong()
                    }
                    imgrepeat.setImageResource(R.drawable.iconrepeat)
                    imgShuffe.setImageResource(R.drawable.iconshuffled)
                }
                imgShuffe.setImageResource(R.drawable.iconshuffled)
                random=true
            }
            else {
                imgShuffe.setImageResource(R.drawable.iconsuffle)
                media?.setOnCompletionListener {
                    position++
                    nextPlay()
                    TimeSong()
                }
                random=false
            }
        }
        imgnext.setOnClickListener {
            position++
            nextPlay()
            TimeSong()
        }
        imgpreview.setOnClickListener {
            position--
            previewPlay()
            TimeSong()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) { // khi dang thay doi
//                val simpleDateFormat= SimpleDateFormat("mm:ss")
//                Log.d("test","${media.currentPosition}")
//                txtViewTime.text ="${simpleDateFormat.format(p1)}"
//                txtViewTime.setVisibility(View.VISIBLE)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) { // khi bat dau cham
            }

            override fun onStopTrackingTouch(p0: SeekBar?) { // khi cham ket thuc
                    media.seekTo(seekBar.progress)
            }

        })



        eventClick()

    }

    private fun previewPlay() {
        if(position==-1){
            position=arrBaiHat.size-1
        }
        media?.reset()
        media = MediaPlayer.create(this,arrBaiHat.get(position).linkBaiHat!!.toUri())
//        media?.setOnCompletionListener {
//            nextPlay()
//        }

        diaNhacFragment=DiaNhacFragment(arrBaiHat.get(position))

        Picasso.with(DiaNhacFragment(arrBaiHat[position]).context).load(arrBaiHat[position].hinhBaiHat).into(circleView)
        toolBarPlayMusic?.setTitle(arrBaiHat[position].tenBaiHat)
        media?.start()
    }

    private fun nextPlay() {
        if(position==arrBaiHat.size){
            position=0
        }
        media?.reset()
        media = MediaPlayer.create(this,arrBaiHat.get(position).linkBaiHat!!.toUri())
        media?.setOnCompletionListener {
            nextPlay()
        }


        Picasso.with(DiaNhacFragment(arrBaiHat[position]).context).load(arrBaiHat[position].hinhBaiHat).into(circleView)
        toolBarPlayMusic?.setTitle(arrBaiHat[position].tenBaiHat)
        media?.start()
    }

    private fun eventClick() {
        threadHandler.postDelayed({
            threadHandler.removeCallbacks(UpdateSeekBarThread())
        },50)
    }

    private fun init() {
        setSupportActionBar(toolBarPlayMusic)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("")
        toolBarPlayMusic.setNavigationOnClickListener{
            finish()
            media.stop()
            arrBaiHat.clear()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
    private fun TimeSong() {
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        try{

            txtviewAllTime.setText(simpleDateFormat.format(media.duration))// media.duration tra ve
            seekBar.max=media.duration
        }
        catch (e:Exception){
            txtviewAllTime.setText(simpleDateFormat.format(0))
        }
    }
    inner class  PlayMp3() : AsyncTask<String, Void, String>() { // inner cho phep lay cac thuoc tinh o lop cha

        override fun doInBackground(vararg p0: String?): String? {
            return p0[0]

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
           try {
               media.setAudioStreamType(AudioManager.STREAM_MUSIC)
               media.setOnCompletionListener { // kiem tra loi load
                   media.stop()// co loi thi dung va khoi chay lai
                   media.reset()
               }
               media.setDataSource(result) // khoi tao du lieu
               media.prepare() //  ham de phat nhac
               media.start() //  chay
               TimeSong()

           }
           catch (e:Exception){
               Log.d("Play Nhac","Err")
           }
        }




    }
    inner class UpdateSeekBarThread : Runnable {
        override fun run() {
            val currentPosition: Int = media.getCurrentPosition();
//            val currentPositionStr: String = currentPosition.toString()
            val simpleDateFormat:SimpleDateFormat= SimpleDateFormat("mm:ss")

            txtViewTime.text =  simpleDateFormat.format(currentPosition)//"${SimpleDateFormat("mm:ss").format(currentPositionStr)}"
//            Log.d("TestRunnable","${SimpleDateFormat("mm:ss").format(currentPositionStr)}")
            seekBar.setProgress(currentPosition)

            // Ngừng thread 50 mili giây.
            threadHandler.postDelayed(this, 50) }
    }
//    private fun createPlayer(){
//
//        var songsJob = async {
//            val songFinder = MusicFinder(contentResolver)
//            songFinder.prepare()
//            songFinder.allSongs
//        }
//
//        launch(kotlinx.coroutines.experimental.android.UI){
//            val songs = songsJob.await()
//
//            val playerUI = object:AnkoComponent<MainActivity>{
//                override fun createView(ui: AnkoContext<MainActivity>)= with(ui) {
//
//                    relativeLayout{
//                        backgroundColor = Color.BLACK
//
//                        albumArt = imageView{
//                            scaleType = ImageView.ScaleType.FIT_CENTER
//                        }.lparams(matchParent, matchParent)
//
//                        verticalLayout{
//                            backgroundColor = Color.parseColor("#99000000")
//                            songTitle = textView{
//                                textColor = Color.WHITE
//                                typeface = Typeface.DEFAULT_BOLD
//                                textSize = 18f
//                            }
//
//                            songArtist = textView{
//                                textColor = Color.WHITE
//                            }
//
//                            linearLayout{
//                                playButton = imageButton{
//                                    imageResource = R.drawable.ic_play_arrow_black_24dp
//                                    onClick {
//                                        playOrPause()
//                                    }
//                                }.lparams(0, wrapContent,0.5f)
//
//                                shuffleButton = imageButton{
//                                    imageResource = R.drawable.ic_shuffle_black_24dp
//                                    onClick {
//                                        playRandom()
//                                    }
//                                }.lparams(0, wrapContent,0.5f)
//                            }.lparams(matchParent, wrapContent){
//                                topMargin = dip(5)
//                            }
//
//
//
//                        }.lparams(matchParent, wrapContent){
//                            alignParentBottom()
//                        }
//                    }
//
//                }
//                fun playRandom(){
//                    Collections.shuffle(songs)
//                    val song = songs[0]
//                    mediaPlayer?.reset()
//                    mediaPlayer = MediaPlayer.create(ctx,song.uri)
//                    mediaPlayer?.setOnCompletionListener {
//                        playRandom()
//                    }
//                    albumArt?.imageURI = song.albumArt
//                    songTitle?.text = song.title
//                    songArtist?.text = song.artist
//                    mediaPlayer?.start()
//                    playButton?.imageResource = R.drawable.ic_pause_black_24dp
//                }
//
//                fun playOrPause(){
//                    var songPlaying:Boolean? = mediaPlayer?.isPlaying
//
//                    if(songPlaying == true){
//                        mediaPlayer?.pause()
//                        playButton?.imageResource = R.drawable.ic_play_arrow_black_24dp
//                    }
//                    else{
//                        mediaPlayer?.start()
//                        playButton?.imageResource = R.drawable.ic_pause_black_24dp
//                    }
//                }
//            }
//            playerUI.setContentView(this@MainActivity)
//            playerUI.playRandom()
//
//
//        }
//    }
}
