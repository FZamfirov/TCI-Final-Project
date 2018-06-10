import Entities.Item;
import Entities.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WebCrawler implements IWebCrawler {

    private String baseUrl;

    public WebCrawler(String baseUrl) {
        this.baseUrl=baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public ArrayList<Document> craw(String baseURL) throws IOException {
        ArrayList<Document> result= new ArrayList<Document>();

        Document allobjects;
        allobjects = Jsoup.connect(baseURL).get();

        ArrayList<Element> objects = allobjects.getElementsByClass("section catalog page").first().select("ul").first()
                .select("li").select("a");

        for (int i=0;i<objects.size();i++){
            String url = objects.get(i).attr("href");

            Document tempobj = Jsoup.connect("http://localhost:8012/tcisite/" + url).get();

            result.add(tempobj);
        }

        return result;
    }

    public ArrayList<Item> ScrapeInformation() throws IOException {
        ArrayList<Item> result = new ArrayList<Item>();
        ArrayList<Document> objects;
        objects = craw(baseUrl);

        for(Document temp:objects){
            Element innerHTML = temp.body().getElementsByClass("media-details").first();
            String objname = innerHTML.select("h1").first().text();
            ArrayList<Element> tablesElements = innerHTML.select("table").first().select("tr");


            String elementName = tablesElements.get(0).select("th").first().text();
            String elementContent = tablesElements.get(0).select("td").first().text();
            Item item=null;

            if(elementName.equals("Category") && elementContent.equals("Music")){
                item = new Music();
                item.setName(objname);
            }
            //TODO other types

            for (int y=1;y<tablesElements.size();y++){
                elementName = tablesElements.get(y).select("th").first().text();
                elementContent = tablesElements.get(y).select("td").first().text();
                if(item instanceof Music){
                    switch (elementName){
                        case "Genre":
                            item.setGenre(elementContent);
                            break;
                        case "Format":
                            item.setFormat(elementContent);
                            break;
                        case "Year":
                            item.setYear(Integer.parseInt(elementContent));
                            break;
                        case "Artist":
                            ((Music) item).setArtist(elementContent);
                            break;
                    }
                }
            }
            result.add(item);
        }
        return result;
    }


}
