package util;


import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbDocument;

import javax.security.auth.login.Configuration;

public class DBUtil {

    private static HttpClient httpClient = new StdHttpClient.Builder()
            .host("localhost")
            .port(5984)
            .build();

    private static CouchDbInstance dbInstance;
    private static CouchDbConnector db;


    static {
        try {
             dbInstance = new StdCouchDbInstance(httpClient);
             db = new StdCouchDbConnector("pos_db", dbInstance);

        } catch ( Throwable ex ) {
            System.err.println ( "Failed to create Session Factory!" + ex );
            throw new ExceptionInInitializerError( ex );
        }
    }

}







