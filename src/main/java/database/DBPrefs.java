package database;

/**
 * Created by ahmetkucuk on 6/6/17. Modified by SunithaBasodi on 10/28/2017.
 */
public class DBPrefs {

    public static final String CONNECTION_LOCAL = "local";
    public static final String CONNECTION_DMLAB_SERVER = "dmlab";

    public static final String CONNECTION_TYPE = CONNECTION_LOCAL;
    //public static final String CONNECTION_TYPE = CONNECTION_DMLAB_SERVER;

    // To run locally
    public static final String MYSQL_URL_DMLAB = "jdbc:mysql://dmlab.cs.gsu.edu:3306/image_db";
    public static final String DB_HOST_DMLAB = "dmlab.cs.gsu.edu";

    // To run on dmlab server
    public static final String MYSQL_URL_SERVER = "jdbc:mysql://mysql:3306/image_db";
    public static final String DB_HOST_POSTGRES = "postgres-exposed";

    public static final String DB_HOST = CONNECTION_TYPE.equals(CONNECTION_DMLAB_SERVER) ? DB_HOST_POSTGRES
            : DB_HOST_DMLAB;

    public static final String MYSQL_URL = CONNECTION_TYPE.equals(CONNECTION_DMLAB_SERVER) ? MYSQL_URL_SERVER
            : MYSQL_URL_DMLAB;

    public static final String DB_NAME = "isd";
    public static String DB_USERNAME = "postgres";
    public static String DB_USER_PASSWORD = "r3mot3p8sswo4d";
}
