import Entities.Item;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class WebCrawlerTest {
    static final String BASE_URL="http://localhost:8012/tcisite/catalog.php";
    static final String SPEC_NAME="Elvis Forever";


    @Test
    public void getBaseURLTest(){
        String baseUrl =BASE_URL;
        WebCrawler testCrawler = new WebCrawler(baseUrl);

        Assert.assertEquals("Expected "+baseUrl+",but received "+testCrawler.getBaseUrl(),baseUrl,testCrawler.getBaseUrl());
    }

    @Test
    public void scrapeAllItemsTest() throws IOException {

        Item testItem = Mockito.mock(Item.class);
        when(testItem.getName()).thenReturn("Beethoven: Complete Symphonies");

        WebCrawler testCrawler = new WebCrawler(BASE_URL);

        ArrayList<Item> result =  testCrawler.ScrapeInformation();
        Assert.assertEquals("Expected "+testItem.getName()+",but received "+ result.get(0).getName(),testItem.getName(), result.get(0).getName());
    }

    @Test
    public void scrapeSpecificItemTest() throws IOException {
        WebCrawler testCrawler = new WebCrawler(BASE_URL);

        Item result = testCrawler.ScrapeInformationFromName("Elvis Forever");

        Assert.assertEquals("Expected "+ SPEC_NAME + ",but received " + result.getName(),SPEC_NAME,result.getName());
    }

    @Test
    public void scrapeSpecificItemFailTest() throws IOException {
        WebCrawler testCrawler = new WebCrawler(BASE_URL);

        Item result = testCrawler.ScrapeInformationFromName("There is no such name");

        Assert.assertEquals("Expected "+ null + ",but received " + result,null,result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void scrapeSpecificItemExceptionTest() throws IOException {
        WebCrawler testCrawler = new WebCrawler("Test");

        Item result = testCrawler.ScrapeInformationFromName("Test");

           }


}
