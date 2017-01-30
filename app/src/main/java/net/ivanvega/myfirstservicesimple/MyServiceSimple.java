package net.ivanvega.myfirstservicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SERVIDOR on 09/08/2016.
 */
public class MyServiceSimple extends Service {

//    private Handler handler = new Handler();
    private Handler    handler = new Handler();



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYSIMPLESERVICE","SERVICIO NICIADO");
        Toast.makeText(this, "SERVICIO INICIADO", Toast.LENGTH_LONG).show();

        handler.postDelayed(runnable, 5000);

//        handler.postDelayed(runnable, 100);
//

         return START_NOT_STICKY;
//        return START_STICKY;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("HILOCORRIENdo", "HILOCORRIENdo");
            handler.postDelayed(this,2000);
        }

    };



//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//      /* do what you need to do */
//
//            Log.d("HOLA", "CADA SEGUNDO");
//
//      /* and here comes the "trick" */
//            handler.postDelayed(this, 1000);
//        }
//    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MYSIMPLESERVICE","SERVICIO PARADO");
        Toast.makeText(this, "SERVICIO PARADO", Toast.LENGTH_LONG).show();
    }
}
