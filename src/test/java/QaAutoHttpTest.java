import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class QaAutoHttpTest extends BaseTest{

    @Test
    public void getTest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        ClassicHttpRequest getBrandsRequest = new HttpGet("https://qauto.forstudy.space/api/cars/brands");
        ClassicHttpResponse response = client.execute(getBrandsRequest);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getCode(), HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode brands = mapper.readTree(response.getEntity().getContent());
        boolean containsAudi = false;
        for(JsonNode brand : brands.get("data")) {
            if(brand.get("id").asInt() == 1 && "Audi".equals(brand.get("title").asText())) {
                containsAudi = true;
                break;
            }
        }
        softAssert.assertTrue(containsAudi, "Audi with is = 1 ais absent");
        softAssert.assertAll();
    }
}
