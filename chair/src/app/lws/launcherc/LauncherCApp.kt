package app.lws.launcherc

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.os.Build
import android.util.Log
import com.android.launcher3.BuildConfig
import com.android.quickstep.RecentsActivity

class LauncherCApp : Application() {
    override fun onCreate() {
        super.onCreate()
        isRecentsEnabled = checkRecentsComponent()
    }

    companion object {
        const val TAG = "FreemeLauncher"

        @JvmStatic
        var isRecentsEnabled: Boolean = false
            private set

        @JvmStatic
        lateinit var instance: LauncherCApp
            private set
    }
}

private const val TAG = "LauncherCApp"

@SuppressLint("DiscouragedApi")
private fun LauncherCApp.checkRecentsComponent(): Boolean {

    if (Build.VERSION.SDK_INT !in BuildConfig.QUICKSTEP_MIN_SDK..BuildConfig.QUICKSTEP_MAX_SDK) {
        Log.d(TAG, "API ${Build.VERSION.SDK_INT} unsupported, disabling recents")
        return false
    }

    val resId = resources.getIdentifier("config_recentsComponentName", "string", "android")
    if (resId == 0) {
        Log.d(TAG, "config_recentsComponentName not found, disabling recents")
        return false
    }

    val recentsComponent = ComponentName.unflattenFromString(resources.getString(resId))
    if (recentsComponent == null) {
        Log.d(TAG, "config_recentsComponentName is empty, disabling recents")
        return false
    }

    val isRecentsComponent = recentsComponent.packageName == packageName
            && recentsComponent.className == RecentsActivity::class.java.name
    if (!isRecentsComponent) {
        Log.d(TAG, "config_recentsComponentName ($recentsComponent) is not this Launcher, disabling recents")
        return false
    }
    Log.d(TAG, "config_recentsComponentName ($recentsComponent) is  this Launcher, enable recents")
    return true
}