package com.example

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Consumer
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class Receiver {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv)
            throws java.io.IOException,
                    java.lang.InterruptedException {

        String topic        = "amp.topic";
        String content      = "Message from MqttPublishSample";
        int qos             = 0;  //setting to 0 fixed error
        String broker       = "tcp://localhost:1883";
        String clientId     = "JavaSample"+Math.random();
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient
        try {
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Subscribing to "+topic);

            sampleClient.subscribe(topic)
            sampleClient.callback = new MyCallback()

            System.out.println("Subscribed")
            while(true) {
                sleep(1000);
            }
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            sampleClient.publish(topic, message);

        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
        finally {
            if(sampleClient) {
                sampleClient.disconnect();
            }
            System.out.println("Disconnected");
        }
        System.exit(0);
    }

    static class MyCallback implements MqttCallback {

        @Override
        void connectionLost(Throwable cause) {
            cause.stackTrace
        }

        @Override
        void messageArrived(String topic, MqttMessage message) throws Exception {

            println "Recieved: $message"


        }

        @Override
        void deliveryComplete(IMqttDeliveryToken token) {
            println "delivery complete: $token.messageId -- $token.message"
            token.messageId
        }
    }
}
