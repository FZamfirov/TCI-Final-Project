import Entities.Item;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class WebCrawlerTest {
    static final String BASE_URL="http://localhost:8012/tcisite/catalog.php";


    @Test
    public void getBaseURLTest(){
        String baseUrl =BASE_URL;
        WebCrawler testCrawler = new WebCrawler(baseUrl);

        Assert.assertEquals("Expected " + baseUrl + ",but received " + testCrawler.getBaseUrl(),baseUrl,testCrawler.getBaseUrl());
    }

    @Test
    public void scrapeInformationTest() throws IOException {
        Item testItem = Mockito.mock(Item.class);
        when(testItem.getName()).thenReturn("Beethoven: Complete Symphonies");
        WebCrawler testCrawler = new WebCrawler(BASE_URL);

        ArrayList<Item> result =  testCrawler.ScrapeInformation();
        Assert.assertEquals("Expected " + testItem.getName() + ",but received " + result.get(0).getName(),testItem.getName(), result.get(0).getName());
    }

}
