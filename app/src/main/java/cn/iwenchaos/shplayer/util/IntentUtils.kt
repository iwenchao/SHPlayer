package cn.iwenchaos.shplayer.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build

/**
 * created by yuchengren on 2019/5/7
 */
fun Context.getLaucherIntent(): Intent? {
    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
    val packageManager = packageManager
    var packageinfo: PackageInfo? = null
    try {
        packageinfo = packageManager.getPackageInfo(packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    if (packageinfo == null) {
        return null
    }

    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
    val resolveIntent = Intent(Intent.ACTION_MAIN, null)
    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    resolveIntent.setPackage(packageinfo.packageName)

    // 通过getPackageManager()的queryIntentActivities方法遍历
    val resolveinfoList = packageManager.queryIntentActivities(resolveIntent, 0)

    val resolveinfo = resolveinfoList.iterator().next()
    if (resolveinfo != null) {
        // packagename = 参数packname
        val packageName = resolveinfo.activityInfo.packageName
        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
        val className = resolveinfo.activityInfo.name
        // LAUNCHER Intent
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        // 设置ComponentName参数1:packagename参数2:MainActivity路径
        val cn = ComponentName(packageName, className)

        intent.component = cn
        return intent
    }
    return null
}

fun Context.startSettingWifi() {
    val intent: Intent
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1 && Build.MANUFACTURER == "Meizu") {
        intent = Intent(android.provider.Settings.ACTION_WIFI_SETTINGS)
    } else {
        intent = Intent()
        val component = ComponentName(
            "cn.iwenchaos.shplayer",
            "cn.iwenchaos.shplayer.module.core.android.SHPlayerActivity"
        )
        intent.component = component
        intent.action = "android.intent.action.VIEW"
    }
//
//    if(this is ContextThemeWrapper){
//        if(baseContext is Activity){
//            baseContext.startActivityForResult()
//        }else if(baseContext is Fragment){
//            baseContext.startActivityForResult()
//        }
//    }

    if (packageManager.resolveActivity(intent, 0) != null) {
        startActivity(intent)
    }

}

/**
 * 跳转设置数据流量
 */
fun Context.startSettingDataRoaming() {
    val intent: Intent
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1 && Build.MANUFACTURER != "Meizu") {
        intent = Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS)
    } else {
        intent = Intent()
        val component =
            ComponentName("com.android.settings", "com.android.settings.WirelessSettings")
        intent.component = component
        intent.action = "android.intent.action.VIEW"
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

