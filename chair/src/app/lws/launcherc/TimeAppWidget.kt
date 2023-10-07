package app.lws.launcherc

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.text.format.DateFormat
import android.widget.RemoteViews
import com.android.launcher3.R

class TimeAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        updateAppWidget(context, appWidgetManager, appWidgetId)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val wm: AppWidgetManager = AppWidgetManager.getInstance(context) ?: return

        val provider = ComponentName(context, javaClass)
        val widgetIds: IntArray = wm.getAppWidgetIds(provider)

        when (intent.action) {
            Intent.ACTION_LOCALE_CHANGED -> onUpdate(context, wm, widgetIds)
        }
    }


}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val formatDate =
        DateFormat.getBestDateTimePattern(context.resources.configuration.locale, "MMdd")

    val calendarUri =
        ContentUris.appendId(CalendarContract.CONTENT_URI.buildUpon().appendPath("time"), 0).build()
    val calendarIntent = Intent(Intent.ACTION_VIEW)
        .setData(calendarUri)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)

    val alarmsIntent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)


    val views = RemoteViews(context.packageName, R.layout.time_app_widget)
    views.setTextColor(R.id.time, Color.WHITE)
    views.setTextColor(R.id.date, Color.WHITE)
    views.setCharSequence(R.id.date, "setFormat24Hour", "$formatDate EEE")
    views.setCharSequence(R.id.date, "setFormat12Hour", "$formatDate EEEa")
    views.setOnClickPendingIntent(
        R.id.time,
        PendingIntent.getActivity(context, 2, alarmsIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    )
    views.setOnClickPendingIntent(
        R.id.date,
        PendingIntent.getActivity(context, 1, calendarIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    )
    appWidgetManager.updateAppWidget(appWidgetId, views)
}


