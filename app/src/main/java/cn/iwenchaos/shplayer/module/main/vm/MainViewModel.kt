package cn.iwenchaos.shplayer.module.main.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Created by:  awen on 2020/5/20  9:30 AM
 * email     :  liuwenchao@mockuai.com
 * Describe  :
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {


    fun getEntranceList(): MutableList<String> {
        return mutableListOf<String>().apply {
            add("原生播放器视频")
        }


    }


}