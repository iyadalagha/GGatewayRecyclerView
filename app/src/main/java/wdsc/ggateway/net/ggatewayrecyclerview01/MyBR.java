package wdsc.ggateway.net.ggatewayrecyclerview01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by PROBOOK on 8/20/2017.
 */

public class MyBR extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Power connected", Toast.LENGTH_LONG).show();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentText("Power connected...");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setTicker("ticker");
            builder.setContentTitle("Title");
            builder.setColor(Color.BLUE);
            builder.setContentInfo("Info");
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.google.com"));
            PendingIntent pIntent = PendingIntent.getActivity(context,0,i,0);
            builder.setContentIntent(pIntent);
            Notification n = builder.build();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1,n);

        }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_LONG).show();
        }else if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null && info.isConnected()){
                Toast.makeText(context, "Connected to the internet", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
            }
        }else if(intent.getAction().equals("android.intent.action.PHONE_STATE")){
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            manager.listen(new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
}
