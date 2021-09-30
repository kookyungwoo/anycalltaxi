package call.taxi.service.external;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFallback implements PaymentService {
    @Override
    public void pay(Payment payment) {
        
        System.out.println("###########################################################");
        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
        System.out.println("###########################################################");
    }
}
