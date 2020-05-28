package org.tensorflow.lite.examples.detection;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.android.service.MqttService;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MqttActivity extends Thread {

    public final static String ipAddress = "192.168.0.101";

    private final String protocol = "tcp";
    private final String port = "1883";
    private final String url = protocol + "://" + ipAddress + ":" + port;

    private static final String TAG = "MainActivity";
    private final String clientId = MqttClient.generateClientId();
    private Context context;

    private final String topic = "forest";
    private String payload;

    public MqttActivity (Context context, String message) {
        payload = message;
        this.context = context;
    }

    public void run () {
        System.out.println("Inside run");
        final MqttAndroidClient client =
                new MqttAndroidClient(context, url, clientId);
        try {
            System.out.println("Inside try");
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    System.out.println("Inside success");
                    byte[] encodedPayload = new byte[0];
                    try {
                        encodedPayload = payload.getBytes("UTF-8");
                        MqttMessage message = new MqttMessage(encodedPayload);
                        client.publish(topic, message);
                        System.out.println("message published");
                    } catch (NullPointerException | UnsupportedEncodingException | MqttException e) {
                        System.out.println("error occured");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    System.out.println("Inside failure");
                    Log.d(TAG, String.valueOf(exception));
                }
            });
        } catch (MqttException e) {
            System.out.println("Inside catch");
            e.printStackTrace();
        }
    }

}
