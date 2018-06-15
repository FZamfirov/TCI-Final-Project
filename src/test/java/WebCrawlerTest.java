import Entities.Item;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import Implementation.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class WebCrawlerTest {
    static final String BASE_URL="http://localhost:8012/tcisite/catalog.php";
    static final String NUM_OF_ITEMS = "12";



    @Test
    public void getBaseURLTest() throws IOException {
        WebCrawler testCrawler = new WebCrawler(BASE_URL);

        Assert.assertEquals("Expected " + BASE_URL + ",but received " + testCrawler.getBaseUrl(),BASE_URL,testCrawler.getBaseUrl());
    }

//    @Test
//    public void amountOfCrawledItemsTest() throws IOException {
//        WebCrawler testCrawler = new WebCrawler(BASE_URL);
//
//        ArrayList<Document> result = testCrawler.craw();
//
//        Assert.assertEquals("Expected " + NUM_OF_ITEMS + ",but received " + result.size(),Integer.parseInt(NUM_OF_ITEMS),result.size());
//
//    }




}
