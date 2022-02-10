package com.example.lab6_exercise;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private final DemoBinder binder = new DemoBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }


    class MyBinder extends Binder implements IBinder {
        public MyService getService() {
            return MyService.this;
        }
    }

    class DemoBinder extends Binder implements IBinder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private void handleActionDemo(String goodbyeMessage) {
        Toast.makeText(MyService.this, "Service Started", Toast.LENGTH_SHORT).show();

        synchronized (this) {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stopSelf();
    }
}