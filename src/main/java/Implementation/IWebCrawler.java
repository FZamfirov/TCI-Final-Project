import Entities.Item;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public interface IWebCrawler {

    String baseURL = null;


    public ArrayList<Document> craw(String baseURL) throws IOException;


}
