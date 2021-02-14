package fooddeliveryfive;

import fooddeliveryfive.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDelivered_(@Payload Delivered delivered){

        if(delivered.isMe()){
            System.out.println("##### listener  : " + delivered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryRequested_(@Payload DeliveryRequested deliveryRequested){

        if(deliveryRequested.isMe()){
            System.out.println("##### listener  : " + deliveryRequested.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMenuRegistered_(@Payload MenuRegistered menuRegistered){

        if(menuRegistered.isMe()){
            System.out.println("##### listener  : " + menuRegistered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMenuDeleted_(@Payload MenuDeleted menuDeleted){

        if(menuDeleted.isMe()){
            System.out.println("##### listener  : " + menuDeleted.toJson());
        }
    }

}
