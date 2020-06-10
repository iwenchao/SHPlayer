package cn.iwenchaos.shplayer.module.core.android

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.iwenchaos.shplayer.R
import cn.iwenchaos.shplayer.base.BaseActivity
import cn.iwenchaos.shplayer.databinding.ActivityShPlayerBinding
import cn.iwenchaos.shplayer.util.ToastHelper
import kotlinx.android.synthetic.main.activity_sh_player.*
import java.util.*

/**
 * Created by:  awen on 2020/5/21  10:51 AM
 * email     :  liuwenchao@mockuai.com
 * Describe  :
 */
class SHPlayerActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SHPlayerActivity::class.java))
        }
    }


    var viewDataBinding: ActivityShPlayerBinding? = null
    val videoPath = "/storage/emulated/0/DCIM/Camera/VID20200518200617.mp4"
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sh_player)

        //设置media player
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(videoPath)
        mediaPlayer?.setOnPreparedListener {
            ToastHelper.show("视频准备完成")
        }
        //设置surface
        surfaceView.run {
            holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceChanged(
                    holder: SurfaceHolder?,
                    format: Int,
                    width: Int,
                    height: Int
                ) {

                }

                override fun surfaceDestroyed(holder: SurfaceHolder?) {
                }

                override fun surfaceCreated(holder: SurfaceHolder?) {
                    val surface = holder?.surface
                    mediaPlayer?.setSurface(surface)
                }

            })
        }
    }

    fun onViewClick(view: View) {
        when (view.id) {
            R.id.tvPrepare -> {
                mediaPlayer?.prepareAsync()
            }
            R.id.tvPlay -> {
                mediaPlayer?.start()
            }
            R.id.tvPause -> {
                mediaPlayer?.pause()
            }
            R.id.tvStop -> {
                mediaPlayer?.stop()
            }

        }

    }

    private fun releasePlayer() {
        mediaPlayer?.run {
            stop()
            release()
        }
        mediaPlayer = null
    }


    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()

    }


}