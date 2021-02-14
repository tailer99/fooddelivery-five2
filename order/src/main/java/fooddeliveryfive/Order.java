package fooddeliveryfive;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long menuId;
    private Integer qty;
    private String status;
    private Long deliveryId;

    @PostPersist
    public void onPostPersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit(); 


    }

    @PreUpdate
    public void onPreUpdate(){

        if ("OrderCancelled".equals(this.status)){
            System.out.println("##### Order status1 : " + this.status);
            
            OrderCancelled orderCancelled = new OrderCancelled();
            BeanUtils.copyProperties(this, orderCancelled);
            orderCancelled.publishAfterCommit();

            //Following code causes dependency to external APIs
            // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

            fooddeliveryfive.external.Delivery delivery = new fooddeliveryfive.external.Delivery();
            // mappings goes here
            delivery.setId(this.getDeliveryId());
            delivery.setStatus(this.status);
            OrderApplication.applicationContext.getBean(fooddeliveryfive.external.DeliveryService.class)
                .cancelDelivery(delivery);
            
        } else if ("Delivered".equals(this.status)){
            System.out.println("##### Order status2 : " + this.status);
            
        } else if ( "DeliveryCanceled".equals(this.status)){
            System.out.println("##### Order status3 : " + this.status);
            
        } else {
            System.out.println("##### Order status4 : " + this.status);
        }


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }




}
