import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by marcosarruda on 3/16/15.
 */
public class MqttSubscriberSample implements MqttCallback {

    MqttClient client;

    public MqttSubscriberSample() {
    }

    public static void main(String[] args) {
        new MqttSubscriberSample().doDemo();
    }

    public void doDemo() {

        String topic        = "MARCOS_TESTEEEEE";
        //String broker       = "tcp://iot.eclipse.org:1883";
        String broker       = "tcp://localhost:1883";
        String clientId     = "Subscriber";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.connect(connOpts);
            client.setCallback(this);
            client.subscribe(topic);
            //MqttMessage message = new MqttMessage();
            //message.setPayload("A single message from my computer - Marcos".getBytes());
            //client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
