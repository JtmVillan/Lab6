package com.example.lab6_exercise;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    private static final String ACTION_DEMO = "edu.ucsd.cse110.lab6_services_intent.action.DEMO";

    private static final String GOODBYE_MESSAGE = "edu.ucsd.cse110.lab6_services_intent.extra.GOODBYE_MESSAGE";
    private String goodbyeMessage;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionDemo(Context context, String goodbyeMessage) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_DEMO);
        intent.putExtra(GOODBYE_MESSAGE, goodbyeMessage);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals(ACTION_DEMO)) {
                this.goodbyeMessage = intent.getStringExtra(GOODBYE_MESSAGE);
                handleActionDemo(goodbyeMessage);
            }   else {

            }
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyIntentService.this, goodbyeMessage, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void handleActionDemo(String goodbyeMessage) {
        Toast.makeText(MyIntentService.this, "Service Started", Toast.LENGTH_SHORT).show();

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