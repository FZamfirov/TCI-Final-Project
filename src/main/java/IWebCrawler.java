import Entities.Item;

import java.io.IOException;
import java.util.ArrayList;

public interface IWebCrawler {

    String baseURL = null;


    public ArrayList<Item> craw(String baseURL) throws IOException;


}
