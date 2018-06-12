package WCA_REST;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Implementation.WebCrawler;
@Path("wca")
public class RestService {



        /**
         * Method handling HTTP GET requests. The returned object will be sent
         * to the client as "text/plain" media type.
         *
         * @return String that will be returned as a text/plain response.
         */



        @GET
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces("application/json")
        public Response getAll(){

            return Response.status(201).build();

        }
        @Path("/{Name}")
        @GET
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces("application/json")
        public Response getSinglePerson(@PathParam("Name") String Name){

                return Response.status(204).build();

        }

    }


