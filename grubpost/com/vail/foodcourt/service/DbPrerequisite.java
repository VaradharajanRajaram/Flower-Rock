/**
 * 
Coded By: Varadharajan Rajaram

Coded Date: Jul 9, 2014 

Coded Time: 7:20:02 AM

 */
package com.vail.foodcourt.service;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class DbPrerequisite  {
	
	private static final Logger log=Logger.getLogger(new Object() { }.getClass().getSimpleName());
	public static String HOST = "localhost";
	public static String URL = "mongodb://localhost:27017";
	public static Integer PORT = 27017;
	public static String DB = "mongodb";
	public static String COLLECTION_MEASURINGS = "measurings";
	public static String COLLECTION_STATIONS = "stations";
	public static Integer BUFFER_SIZE = 20000;
	/** Used to include a field in a response. */
     static final Integer INCLUDE = Integer.valueOf(1);
    /** Count the number of times initialized to teardown on the last {@link #cleanup()}. */
	static final AtomicInteger initCount = new AtomicInteger(0);
	  /** The default write concern for the test. */
	 static WriteConcern writeConcern;
	 public static MongoClient mongo;
	 public static String WRITECONCERNTYPE;
	 
	 public static Integer MAXCONNECTIONS;
	/**
	 * DataTypes used in the time series data
	 * 
	 * @author s1ck
	 * 
	 */
	public static enum DataType {
		UDC {
			public String toString() {
				return "udc";
			}
		},
		PDC {
			public String toString() {
				return "pdc";
			}
		},
		PAC {
			public String toString() {
				return "pac";
			}
		},
		TEMP {
			public String toString() {
				return "temp";
			}
		},
		GAIN {
			public String toString() {
				return "gain";
			}
		}
	}
	/**
	 * Value identifiers (should be as short as possible)
	 */
	public static String TIMESTAMP = "timestamp";
	public static String VALUE = "value";
	public static String STATION_ID = "stationID";
	public static String PART_ID = "partID";
	public static String SERIAL_NO = "serialNo";
	public static String DATATYPE = "datatype";
	public static String OPT_STRING = "opt_string";
	public static String SERIAL_NO_2 = "serialNo2";

	/**
	 * Data paths
	 */
	public static String PATH;

	/**
	 * Eval specific
	 */

	/**
	 * Defines the number of global test runs including import and benchmark
	 */
	public static int GLOBAL_RUNS;
	/**
	 * if set to true, the total range is used in range queries on timestamp
	 */
	public static boolean USE_TOTAL_RANGE;
	/**
	 * Defines the number of randomized benchmark runs
	 */
	public static int LOCAL_RUNS;
	/**
	 * Range size for the queries
	 */
	public static int DAYS;	
	/**
	 * Random seed
	 */
	public static int RANDOM_SEED;	
	/**
	 * True if data shall be imported, else false
	 */
	public static boolean IMPORT;
	public static void initConfig(String configFile) {
		Properties properties = new Properties();
		try {
			/*BufferedInputStream stream;
			stream = new BufferedInputStream(new FileInputStream(configFile));*/
			
			properties = new Properties();
			properties=TemplateMethods.readPropertyFleFrmDrive(configFile);
		
		} catch (Exception e) {
			properties = null;
			String message="Db file not found at Install Path, Properties file folder";
			log.info(message);
			
			System.out.println("Configuration read error.");
			e.printStackTrace();
		}

		if (properties != null) {
			// mongodb connection
			HOST = properties.getProperty("mongodb.host");
			PORT = Integer.parseInt(properties.getProperty("mongodb.port"));
			URL=properties.getProperty("mongodb.url");
			DB = properties.getProperty("mongodb.db");
			WRITECONCERNTYPE=properties.getProperty("mongodb.writeConcern");
			MAXCONNECTIONS=Integer.parseInt(properties.getProperty("mongodb.maxconnections"));
			COLLECTION_MEASURINGS = properties
					.getProperty("mongodb.collection.measurings");
			COLLECTION_STATIONS = properties
					.getProperty("mongodb.collection.stations");
			BUFFER_SIZE = Integer.parseInt(properties
					.getProperty("mongodb.buffersize"));
			
			log.info("Connection Properties has been readed successfully..");
			// dataset properties
			PATH = properties.getProperty("data.path");

			TIMESTAMP = properties.getProperty("data.timestamp");
			VALUE = properties.getProperty("data.value");
			STATION_ID = properties.getProperty("data.stationid");
			PART_ID = properties.getProperty("data.wrid");
			SERIAL_NO = properties.getProperty("data.wrid");
			DATATYPE = properties.getProperty("data.datatype");
			OPT_STRING = properties.getProperty("data.optstring");
			SERIAL_NO_2 = properties.getProperty("data.stringid");

			log.info("Dataset Properties has been readed successfully..");
			// evaluation properties

			GLOBAL_RUNS = Integer.parseInt(properties
					.getProperty("eval.globalruns"));
			LOCAL_RUNS = Integer.parseInt(properties
					.getProperty("eval.localruns"));
			DAYS = Integer.parseInt(properties.getProperty("eval.days"));
			USE_TOTAL_RANGE = Boolean.parseBoolean(properties.getProperty("eval.usetotalrange"));
			RANDOM_SEED = Integer.parseInt(properties.getProperty("eval.seed"));
			IMPORT = Boolean.parseBoolean(properties.getProperty("eval.import"));
			log.info("Evaluation Properties has been readed successfully..");
		}
	}

}
