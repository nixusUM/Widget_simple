package com.exampl.nixus.ykt.temp_widg;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Timer;
import java.util.TimerTask;

public class MyWidgetProvider extends AppWidgetProvider {
    GetWheather in = new GetWheather();
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        Intent intent = new Intent(context.getApplicationContext(),
                UpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
        context.startService(intent);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1,
                90400000);
    }
    private class MyTime extends TimerTask {
        RemoteViews remoteViews;
        AppWidgetManager appWidgetManager;
        ComponentName thisWidget;
        public MyTime(Context context, AppWidgetManager appWidgetManager) {
            this.appWidgetManager = appWidgetManager;
            remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.prj01);
            thisWidget = new ComponentName(context, MyWidgetProvider.class);
        }
        @Override
        public void run() {
            remoteViews.setTextViewText(R.id.update, '+' + in.getInet("http://yabesco.ru/getTemp1.php") + " C" );
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        }
    }
}