import com.chen.xiansen.ProviderPaymentApplication;
import com.chen.xiansen.service.PaymentService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProviderPaymentApplication.class)
public class ApplicationTests {
    @Resource
    public PaymentService service;

    @Test
    public void test() {
        System.out.println(service.getAll());
    }
}
