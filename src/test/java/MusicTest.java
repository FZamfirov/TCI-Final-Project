import Entities.Music;
import org.junit.Assert;
import org.junit.Test;

public class MusicTest {
    static final String TEST_VALUE = "TEST";
    private Music testmusic = new Music();

    @Test
    public void getNameTest(){

        testmusic.setName(TEST_VALUE);

        Assert.assertEquals("Expected " + TEST_VALUE + " ,but received "+ testmusic.getName(), TEST_VALUE,testmusic.getName());
    }

    @Test
    public void getFormatTest(){

        testmusic.setFormat(TEST_VALUE);

        Assert.assertEquals("Expected " + TEST_VALUE + " ,but received "+ testmusic.getFormat(), TEST_VALUE,testmusic.getFormat());
    }

    @Test
    public void getGenreTest(){

        testmusic.setGenre(TEST_VALUE);

        Assert.assertEquals("Expected " + TEST_VALUE + " ,but received "+ testmusic.getGenre(), TEST_VALUE,testmusic.getGenre());
    }
}
