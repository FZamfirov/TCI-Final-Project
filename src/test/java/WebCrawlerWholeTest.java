import Entities.Item;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class WebCrawlerWholeTest {
    static final String BASE_URL="http://localhost:8012/tcisite/catalog.php";


    @Test
    public void getBaseURLTest(){
        String baseUrl =BASE_URL;
        WholeSiteCrawler testCrawler = new WholeSiteCrawler(baseUrl);

        Assert.assertEquals("Expected "+baseUrl+",but received "+testCrawler.getBaseUrl(),baseUrl,testCrawler.getBaseUrl());
    }

    @Test
    public void crawlTest() throws IOException {
        Item testItem = Mockito.mock(Item.class);
        when(testItem.getName()).thenReturn("Beethoven: Complete Symphonies");
        String baseUrl =BASE_URL;
        WholeSiteCrawler testCrawler = new WholeSiteCrawler(baseUrl);

        ArrayList<Item> result =  testCrawler.craw(BASE_URL);
        Assert.assertEquals(testItem.getName(), result.get(0).getName());
    }

}
