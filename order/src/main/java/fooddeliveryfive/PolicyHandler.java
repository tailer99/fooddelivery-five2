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

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MenuRepository menuRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDelivered_UpdateStatus(@Payload Delivered delivered){

        if(delivered.isMe()){
            Order order = new Order();
            order.setId(delivered.getOrderId());
            order.setStatus(delivered.getStatus());

            orderRepository.save(order);

            System.out.println("##### listener UpdateStatus : " + delivered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMenuRegistered_RegisterMenu(@Payload MenuRegistered menuRegistered){

        if(menuRegistered.isMe()){
            Menu menu = new Menu();
            menu.setMenuId(menuRegistered.getMenuId());
            menu.setMenuName(menuRegistered.getMenuName());

            menuRepository.save(menu);
            System.out.println("##### listener RegisterMenu : " + menuRegistered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMenuDeleted_DeleteMenu(@Payload MenuDeleted menuDeleted){

        if(menuDeleted.isMe()){
            Menu menu = new Menu();
            menu.setMenuId(menuDeleted.getMenuId());
            menu.setMenuName(menuDeleted.getMenuName());
            
            menuRepository.delete(menu);
            System.out.println("##### listener DeleteMenu : " + menuDeleted.toJson());
        }
    }

}
