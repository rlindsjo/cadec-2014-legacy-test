package org.callistasoftware.cadec2014.legacy.env;

import java.util.Properties;
import java.util.logging.Logger;

public class Environment {
    private static final int WORK_TIME = 2000;
    private static final Logger LOG = Logger.getLogger("Environment");
    private DbEnv dbEnv;
    private BookParser jsonParser;
    private Properties properties;
    private DataFetcher dataFetcher;
    
    Environment(Properties properties) {
        this.properties = properties;
        setupDB(properties.getProperty("db.connect.url"));
        setupJson(properties.getProperty("json.parser.config"));
        setupDataFetcher(properties.getProperty("datafetcher.template"));
        setupCluster(properties.getProperty("replication.cluster.config"));
    }

    private void setupCluster(String property) {
        simulateWork(WORK_TIME, "replication", property);		
	}

	private void setupDataFetcher(String property) {
        simulateWork(WORK_TIME, "datafetcher", property);
        dataFetcher = new DataFetcher(property);
    }

    private void setupJson(String property) {
        simulateWork(WORK_TIME, "parser", property);
        jsonParser = new BookParser();
    }

    public Properties getProperties() {
        return properties;
    }

    public DbEnv getDbEnv() {
        return dbEnv;
    }

    public BookParser getJsonParser() {
        return jsonParser;
    }

    private void setupDB(String property) {
        simulateWork(WORK_TIME, "database", property);
    }

    private void simulateWork(int milliseconds, String type, String property) {
        LOG.info("Setting up " + type + " using " + property);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
        if (property == null || property.length() == 0) {
            LOG.severe("Error starting " + type + " using " + property);
            throw new IllegalArgumentException("Error starting " + type);
        }
    }

    public DataFetcher getDataFetcher() {
        return dataFetcher;
    }
}
