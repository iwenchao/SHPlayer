package cn.iwenchaos.shplayer.util

import android.widget.Toast
import cn.iwenchaos.shplayer.base.BaseContext

/**
 * Created by:  awen on 2020/6/10  5:25 PM
 * email     :  liuwenchao@mockuai.com
 * Describe  :
 */
object ToastHelper {


    fun show(msg: String?) {
        if (msg.isNullOrEmpty()) {
            return
        }
        Toast.makeText(BaseContext.appContext, msg, Toast.LENGTH_SHORT).show()
    }


}