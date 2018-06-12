package Implementation;
import Entities.Item;
import Entities.Music;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;

public class Scrapper {

    private WebCrawler webCrawler;

    public Scrapper(String baseUrl){
        this.webCrawler= new WebCrawler(baseUrl);
    }

    public ArrayList<Item> ScrapeInformation() throws IOException {
        ArrayList<Item> result = new ArrayList<Item>();
        ArrayList<Document> objects;
        objects = webCrawler.craw();

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

    public Item ScrapeInformationFromName(String Name) throws IOException {
        ArrayList<Document> objects;
        objects = webCrawler.craw();

        for(Document temp:objects){
            Element innerHTML = temp.body().getElementsByClass("media-details").first();
            String objname = innerHTML.select("h1").first().text();
            ArrayList<Element> tablesElements = innerHTML.select("table").first().select("tr");

            if(objname.equals(Name)){

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
                return item;
            }
        }
        return null;
    }
}
