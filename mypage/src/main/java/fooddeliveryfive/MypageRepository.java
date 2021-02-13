package fooddeliveryfive;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MypageRepository extends CrudRepository<Mypage, Long> {

    List<> findByOrderId(Long orderId);
    List<> findByDeliveryId(Long deliveryId);
    List<> findByDeliveryId(Long deliveryId);

        void deleteByStatus(String status);
}