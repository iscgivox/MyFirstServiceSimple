package net.ivanvega.myfirstservicesimple;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by SERVIDOR on 15/08/2016.
 */
public class MyIntentService extends IntentService {

    public  MyIntentService(){
        super("MiServicito");

    }



    @Override
    protected void onHandleIntent(Intent intent) {
        for(int i = 0; i<60; i++){
            try {
                Thread.sleep(1000);
                Log.w("INTENT-SERVICE","Esperando a que muera el servicio "
                        +   String.valueOf(59 - i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
