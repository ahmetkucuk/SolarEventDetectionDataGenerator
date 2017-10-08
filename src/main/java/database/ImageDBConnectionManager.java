package database;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import edu.gsu.dmlab.databases.interfaces.IImageDBConnection;
import edu.gsu.dmlab.databases.interfaces.IImageDBCreator;
import edu.gsu.dmlab.databases.*;

/**
 * Created by ahmetkucuk on 6/6/17.
 */
public class ImageDBConnectionManager {
    DataSource imageDBPoolSourc;
    int imageCacheSize;
    IImageDBConnection imdb;

    public ImageDBConnectionManager() {
        imageDBPoolSourc = createPoolDBSourc();
        int cacheSize = 2;

        IImageDBCreator imgDBCreator = new ImageDBCreator(this.imageDBPoolSourc);
        this.imdb = new HelioviewerPullingImageDBConnection(this.imageDBPoolSourc, imgDBCreator, null, cacheSize);

    }

    public IImageDBConnection getImageDB() {
        return this.imdb;
    }

    public int getCacheSize() {
        return this.imageCacheSize;
    }

    private BasicDataSource createPoolDBSourc() {
        BasicDataSource dbPoolSourc = null;
        dbPoolSourc = new BasicDataSource();

        dbPoolSourc.setSoftMinEvictableIdleTimeMillis(6500);
        dbPoolSourc.setDefaultAutoCommit(true);
        dbPoolSourc.setPoolPreparedStatements(false);
        dbPoolSourc.setDefaultQueryTimeout(6500);
        dbPoolSourc.setMinIdle(650);
        dbPoolSourc.setMaxIdle(6500);
        dbPoolSourc.setMaxTotal(65000);

        dbPoolSourc.setUsername("remoteUser");
        dbPoolSourc.setPassword("r3moteUser");
        dbPoolSourc.setValidationQuery("Select 1;");
        dbPoolSourc.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbPoolSourc.setUrl("jdbc:mysql://dmlab.cs.gsu.edu:3306/image_db");


        return dbPoolSourc;
    }

}

