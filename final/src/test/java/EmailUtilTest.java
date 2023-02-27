import static org.junit.Assert.fail;

import com.eshop.util.EmailUtil;
import org.junit.Test;

public class EmailUtilTest {

    @Test
    public void testSendEmail_validAddress() {
        String recipient = "1295273096@qq.com";
        String subject = "Test email";
        String message = "This is a test email";

        try {
            EmailUtil.sendEmail(recipient, subject, message);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void testSendEmail_invalidAddress() throws Exception {
        String recipient = "abcd@qq.com";
        String subject = "Test email";
        String message = "This is a test email";

        EmailUtil.sendEmail(recipient, subject, message);
    }
}
