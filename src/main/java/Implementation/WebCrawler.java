package Implementation;
import Entities.Item;
import Entities.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This WebCrawler class offers
 * spider functionality and copies all the links
 * from the website
 * <p></p>
 * @author: Filip Zamfirov
 * @since 2018-06-11
 */
public class WebCrawler {

    private String baseUrl;
    private ArrayList<String> queue;
    private ArrayList<String> results = new ArrayList<>();


    public WebCrawler(String baseUrl) {
        this.baseUrl=baseUrl;
        queue=new ArrayList<>();
        queue.add(baseUrl);
    }


    public String getBaseUrl() {
        return baseUrl;
    }


///////////////////////////////////Old way of crawling//////////////////////////////////////////////////////////////////
//    public ArrayList<Document> craw() throws IOException {
//        ArrayList<Document> result= new ArrayList<Document>();
//
//        Document allobjects;
//        allobjects = Jsoup.connect(baseUrl).get();
//
//        ArrayList<Element> objects = allobjects.getElementsByClass("section catalog page").first().select("ul").first()
//                .select("li").select("a");
//
//        for (int i=0;i<objects.size();i++){
//            String url = objects.get(i).attr("href");
//
//            Document tempobj = Jsoup.connect("http://localhost:8012/tcisite/" + url).get();
//
//            result.add(tempobj);
//        }
//
//        return result;
//    }


    /**
     * This is a recursive method that craws through the website and extracts all the links, so that the scrapper class can extract data
     * @return The method returns a list of strings with all the links
     * @throws IOException
     */
    public ArrayList<String> craw() throws IOException {
        //base case
        if(queue.size()<1){
            return results;

        }else{
           String temp = queue.get(0);

                if (!results.contains(temp)){
                    Document tempdoc = Jsoup.connect(temp).get();
                    Elements elements = tempdoc.select("a");

                    for(Element e:elements){
                        //Skips the links for facebook and twitter in the footer
                        if(!e.attr("href").equals("https://www.facebook.com/TeamTreehouse")) {
                            if (!e.attr("href").equals("http://twitter.com/treehouse")) {
                                String newtemp = "http://localhost:8012/tcisite/" + e.attr("href");
                                //Escape infinite recursion
                                if (!queue.contains(newtemp)) {
                                    queue.add(newtemp);
                                }
                            }
                        }

                    }
                    results.add(temp);
                    queue.remove(0);
                    return craw();
                }

            queue.remove(0);
            return craw();
        }
    }

    /**
     * This method is used to copy all the HTML from the links that are already spied/copied from the base url
     * @return It returns a list of type Jsoup.Document with all the web pages
     * @throws IOException
     */
    public ArrayList<Document> getHTMLFromLink() throws IOException {
        ArrayList<Document> temp = new ArrayList<>();

        for(String link:results){
            Document tempobj = Jsoup.connect(link).get();
            temp.add(tempobj);
        }

        return temp;

    }
}
