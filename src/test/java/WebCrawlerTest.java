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
    static final String NUM_OF_ITEMS = "20";
    private WebCrawler testCrawler = new WebCrawler(BASE_URL);


    @Test
    public void getBaseURLTest() throws IOException {

        Assert.assertEquals("Expected " + BASE_URL + ",but received " + testCrawler.getBaseUrl(),BASE_URL,testCrawler.getBaseUrl());
    }

    @Test
    public void amountOfCrawledItemsTest() throws IOException {

        ArrayList<String> result = testCrawler.craw();

        Assert.assertEquals("Expected " + NUM_OF_ITEMS + ",but received " + result.size(),Integer.parseInt(NUM_OF_ITEMS),result.size());

    }

    @Test (expected = IllegalArgumentException.class)
    public void crawlExceptionTest() throws IOException {
        WebCrawler testCrawler = new WebCrawler("This doesn't work");
        testCrawler.craw();
    }

    @Test
    public void getHTMLFromLinkTest() throws IOException {
        ArrayList<String> links = testCrawler.craw();
        ArrayList<Document> documents = testCrawler.getHTMLFromLink();

        Assert.assertEquals(String.valueOf(links.size()),String.valueOf(documents.size()));


    }




}
