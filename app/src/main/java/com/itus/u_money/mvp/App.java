package com.itus.u_money.mvp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class App extends Application {
   private static Context context;
   public static Context getContext() {
      return context;
   }

   @Override
   public void onCreate() {
      super.onCreate();
      context = getApplicationContext();
      Log.d("Debug", "onCreate: ");
   }
}
