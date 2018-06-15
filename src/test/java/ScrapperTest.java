import Entities.Item;
import Implementation.Scrapper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class ScrapperTest {

    static final String BASE_URL="http://localhost:8012/tcisite/catalog.php";
    static final String SPEC_NAME="Elvis Forever";
    static final String NUM_OF_ITEMS = "12";

    public Object[] getParams(){
        return new Object[]{
                new Object[]{"Elvis Forever"},
                new Object[]{"Beethoven: Complete Symphonies"},
                new Object[]{"No Fences"},
                new Object[]{"The Very Thought of You"},
        };
    }

    @Test
    public void scrapeInformationTest() throws IOException {
        Item testItem = Mockito.mock(Item.class);
        when(testItem.getName()).thenReturn("Beethoven: Complete Symphonies");
        Scrapper scrapper = new Scrapper(BASE_URL);

        ArrayList<Item> result =  scrapper.ScrapeInformation();
        Assert.assertEquals("Expected " + testItem.getName() + ",but received " + result.get(0).getName(),testItem.getName(), result.get(0).getName());
    }

    @Test
    public void scrapeAllItemsTest() throws IOException {

        Item testItem = Mockito.mock(Item.class);
        when(testItem.getName()).thenReturn("Beethoven: Complete Symphonies");

        Scrapper scrapper = new Scrapper(BASE_URL);

        ArrayList<Item> result =  scrapper.ScrapeInformation();
        Assert.assertEquals("Expected " + NUM_OF_ITEMS + ",but received " + result.size(),Integer.parseInt(NUM_OF_ITEMS), result.size());
    }

    @Test
    @Parameters(method = "getParams")
    public void scrapeSpecificItemTest(String NAME_PARAM) throws IOException {

        Scrapper scrapper = new Scrapper(BASE_URL);

        Item result = scrapper.ScrapeInformationFromName(NAME_PARAM);

        Assert.assertEquals("Expected "+ NAME_PARAM + ",but received " + result.getName(),NAME_PARAM,result.getName());
    }

    @Test
    public void scrapeSpecificItemFailTest() throws IOException {

        Scrapper scrapper = new Scrapper(BASE_URL);

        Item result = scrapper.ScrapeInformationFromName("There is no such name");

        Assert.assertEquals("Expected "+ null + ",but received " + result,null,result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void scrapeSpecificItemExceptionTest() throws IOException {

        Scrapper scrapper = new Scrapper("Test");
        scrapper.ScrapeInformationFromName("Test");

    }
}
