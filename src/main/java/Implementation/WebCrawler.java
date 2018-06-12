package Implementation;
import Entities.Item;
import Entities.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WebCrawler {

    private String baseUrl;

    public WebCrawler(String baseUrl) {
        this.baseUrl=baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public ArrayList<Document> craw() throws IOException {
        ArrayList<Document> result= new ArrayList<Document>();

        Document allobjects;
        allobjects = Jsoup.connect(baseUrl).get();

        ArrayList<Element> objects = allobjects.getElementsByClass("section catalog page").first().select("ul").first()
                .select("li").select("a");

        for (int i=0;i<objects.size();i++){
            String url = objects.get(i).attr("href");

            Document tempobj = Jsoup.connect("http://localhost:8012/tcisite/" + url).get();

            result.add(tempobj);
        }

        return result;
    }
}
