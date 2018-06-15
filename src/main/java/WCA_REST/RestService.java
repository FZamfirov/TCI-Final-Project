package WCA_REST;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Entities.Item;
import Implementation.Scrapper;
import Implementation.WebCrawler;

import java.io.IOException;
import java.util.ArrayList;

@Path("wca")
public class RestService {
private static final String BASE_URL="http://localhost:8012/tcisite/catalog.php";
private Scrapper scrapper = new Scrapper(BASE_URL);
private WebCrawler webCrawler = new WebCrawler(BASE_URL);

        public RestService() throws IOException {
        }

        /**
         * Method handling HTTP GET requests. The returned object will be sent
         * to the client as "text/plain" media type.
         *
         * @return String that will be returned as a text/plain response.
         */



        @GET
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces("application/json")
        public Response getAll() throws IOException {
            ArrayList<Item> result = scrapper.ScrapeInformation();
            return Response.status(200).entity(result).build();

        }
        @Path("/{Name}")
        @GET
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces("application/json")
        public Response getSinglePerson(@PathParam("Name") String Name) throws IOException {

                Item result = scrapper.ScrapeInformationFromName(Name);
                return Response.status(200).entity(result).build();

        }

    }


