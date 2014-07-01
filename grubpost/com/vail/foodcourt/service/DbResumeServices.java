package com.vail.foodcourt.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.vail.foodcourt.database.MongoDB_Queries;
import com.vail.foodcourt.database.StationInfo;
import com.vail.foodcourt.service.DbPrerequisite.DataType;

public class DbResumeServices {

	private Mongo mongoInstance;

	/**
	 * Reference to the database containing the collections
	 */
	private DB db;

	/**
	 * Path to the data to be imported
	 */
	private String dataPath;

	/**
	 * Information about the stations which can be used for random data
	 * retrieval. Stations depend on the dataset.
	 */
	private List<StationInfo> availableStations;
	/**
	 * In each run the original available stations will be added to the
	 * available stations with an attached run id. This is a workaround to avoid
	 * distinct queries before each run.
	 */
	private List<StationInfo> originalStations;

	private List<String> availableDataTypes;

	private Long lowerTimeBound;

	private Long upperTimeBound;

	/*
	 * Getter Setter
	 */
	public Mongo getM() {
		return mongoInstance;
	}

	public DB getDb() {
		return db;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	/*
	 * Public methods
	 */
	public Mongo initDatabase(String path) throws UnknownHostException,
	MongoException {
// open connection
mongoInstance= new Mongo(DbPrerequisite.HOST, DbPrerequisite.PORT);
// chose database (will be created if not existing
db = mongoInstance.getDB(DbPrerequisite.DB);
// init the list
availableStations = new ArrayList<StationInfo>();
originalStations = new ArrayList<StationInfo>();
// init the available datatypes
availableDataTypes = new ArrayList<String>();

// create collections (if not existing)
if (db.getCollection(DbPrerequisite.COLLECTION_MEASURINGS) == null) {
	db.createCollection(DbPrerequisite.COLLECTION_MEASURINGS, null);
}
if (db.getCollection(DbPrerequisite.COLLECTION_STATIONS) == null) {
	db.createCollection("stations", null);
}

dataPath = path;
db.requestStart();
return mongoInstance;
}

@SuppressWarnings("unchecked")
public void initStations() {
DBCollection measurings = db
		.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);
List<String> stationIDs = measurings
		.distinct(DbPrerequisite.STATION_ID);

for (String station : stationIDs) {
	// get the distinct wr ID (serialNo) and count them
	int wrCount = measurings.distinct(DbPrerequisite.SERIAL_NO,
			new BasicDBObject(DbPrerequisite.STATION_ID, station))
			.size();

	// store the information
	availableStations.add(new StationInfo(station, wrCount));
	originalStations.add(new StationInfo(station, wrCount));
}
}

/**
* Adds any original station (e.g. singwitz) plus the current run-id to the
* available stations, so these will be available in the next test run.
* 
* @param globalRun
*/
public void updateStations(int globalRun) {
for (StationInfo station : originalStations) {
	availableStations.add(new StationInfo(station.getName().substring(
			0, station.getName().length() - 1)
			+ Integer.toString(globalRun), station.getWrCount()));
}
}

public void initTimeRange() {
DBCollection measurings = db
		.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

lowerTimeBound = (Long) measurings.find()
		.sort(new BasicDBObject(DbPrerequisite.TIMESTAMP, 1)).limit(1)
		.next().get(DbPrerequisite.TIMESTAMP);

upperTimeBound = (Long) measurings.find()
		.sort(new BasicDBObject(DbPrerequisite.TIMESTAMP, -1)).limit(1)
		.next().get(DbPrerequisite.TIMESTAMP);
}

@SuppressWarnings("unchecked")
public void initDataTypes() {
DBCollection measurings = db
		.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

List<String> dataTypes = measurings.distinct(DbPrerequisite.DATATYPE);

for (String dataType : dataTypes) {
	// store the information
	availableDataTypes.add(dataType);
}
}

public void shutdown() {
// close connection
mongoInstance.close();
}

public void createIndexes(DB mongoDB) {

// compound index on station, inverter, datatype and timestamp
DBObject index = new BasicDBObject();
index.put(DbPrerequisite.STATION_ID, 1);
index.put(DbPrerequisite.SERIAL_NO, 1);
index.put(DbPrerequisite.DATATYPE, 1);
index.put(DbPrerequisite.TIMESTAMP, 1);

MongoDB_Queries.createIndex(mongoDB,
		DbPrerequisite.COLLECTION_MEASURINGS, index);
}

public void dropIndexes(DB mongoDB) {

DBObject index = new BasicDBObject();
index.put(DbPrerequisite.STATION_ID, 1);
index.put(DbPrerequisite.SERIAL_NO, 1);
index.put(DbPrerequisite.DATATYPE, 1);
index.put(DbPrerequisite.TIMESTAMP, 1);

MongoDB_Queries.dropIndex(mongoDB,
		DbPrerequisite.COLLECTION_MEASURINGS, index);
}

/**
* Processes the queries
* 
* @param mongoDB
*            mongodb connection
*/
public void processQueries(DB mongoDB) {

MongoDB_Queries.initRandom();

// query 1
System.out.println("query 1");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query1(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 2
System.out.println("query 2");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query2(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 3
System.out.println("query 3");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query3(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 4
System.out.println("query 4");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query4(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 5
System.out.println("query 5");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query5(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 6
System.out.println("query 6");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query6(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 7
System.out.println("query 7");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query7(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}

// query 8
System.out.println("query 8");
for (int currentRun = 0; currentRun < DbPrerequisite.LOCAL_RUNS; currentRun++) {
	MongoDB_Queries.query8(mongoDB, availableStations,
			availableDataTypes, lowerTimeBound, upperTimeBound);
}
}

/**
* Loads measuring data from a given CSV file into a mongoDB instance.
* 
* @param csvFile
*            Path (absolute or relative to the csv file)
* @param mongoDB
*            the mongoDB instance
*/
public void importData(String csvFile, DB mongoDB, int globalRun) {
List<BasicDBObject> dbObjectPuffer = new ArrayList<BasicDBObject>(
		DbPrerequisite.BUFFER_SIZE);
BufferedReader bf;
try {
	bf = new BufferedReader(new FileReader(csvFile));

	String line;
	long n = 0L;

	long start = System.currentTimeMillis();
	long diff = 0L;
	while ((line = bf.readLine()) != null) {
		n++;
		dbObjectPuffer.add(createDBObjectFromData(line, globalRun));
		if (dbObjectPuffer.size() == DbPrerequisite.BUFFER_SIZE) {
			// store data and clear buffer
			writeDataToDB(dbObjectPuffer, mongoDB);
			dbObjectPuffer.clear();
			// System.out.print(".");
			if (n / DbPrerequisite.BUFFER_SIZE % 10 == 0) {
				diff = System.currentTimeMillis() - start;
				// System.out.printf(" %d documents inserted in %d seconds\n",
				// n, diff);
				System.out.printf("%d;%d\n", n, diff);
			}
		}
	}
	diff = System.currentTimeMillis() - start;
	System.out.printf(
			"took %d seconds for %d documents (%d documents / s)\n",
			diff / 1000, n, n / (diff / 1000));
	bf.close();
} catch (FileNotFoundException fEx) {
	System.err.println(fEx);
} catch (IOException ioEx) {
	System.err.println(ioEx);
}
}

/*
* Private Helpers
*/

/**
* Creates a Database Object from a given csv String
* 
* @param line
*            CSV data information about the database object
* @return the Database Object
*/
private BasicDBObject createDBObjectFromData(String line, int globalRun) {
String[] documentData = line.split(";");

BasicDBObject dbObj = new BasicDBObject();
// timestamp
dbObj.put(DbPrerequisite.TIMESTAMP, Long.parseLong(documentData[0]));
// value
dbObj.put(DbPrerequisite.VALUE, Integer.parseInt(documentData[1]));
// identifier
String identifier = documentData[2];
if (identifier != null) {
	String[] identifierData = identifier.split("\\.");
	// station_ID (Anlagenname)
	dbObj.put(DbPrerequisite.STATION_ID,
			String.format("%s_%d", identifierData[0], globalRun));
	// partID (Bauteilart)
	dbObj.put(DbPrerequisite.PART_ID, identifierData[1]);
	// serial number (laufende Nummer)
	dbObj.put(DbPrerequisite.SERIAL_NO,
			Integer.parseInt(identifierData[2]));
	// datatype (Datenart)
	dbObj.put(DbPrerequisite.DATATYPE, identifierData[3]);
	// optional data
	if (identifierData[3].equals(DataType.PDC.toString())
			|| identifierData[3].equals(DataType.UDC.toString())) {
		// "string"
		dbObj.put(DbPrerequisite.OPT_STRING, identifierData[4]);
		// serial number 2
		dbObj.put(DbPrerequisite.SERIAL_NO_2, identifierData[5]);
	}
}
return dbObj;
}

/**
* writes a set of documents to the database
* 
* @param documents
*            a set of database objects (documents)
* @param mongoDB
*            mongoDB instance where the documents are stored
*/
private void writeDataToDB(List<BasicDBObject> documents, DB mongoDB) {
DBCollection measurementCollection = mongoDB
		.getCollectionFromString(DbPrerequisite.COLLECTION_MEASURINGS);

for (DBObject dbObj : documents) {
	measurementCollection.save(dbObj);
}
}

/*
* Main
*/

/**
* Main program
* 
* @param args
* @throws UnknownHostException
* @throws MongoException
*/
public static void main(String[] args) throws UnknownHostException,
	MongoException {
	System.out.println("***********");
	System.out.println(new Object() { }.getClass().getSimpleName());
	System.out.println("***********");
	DbResumeServices eval = new DbResumeServices();

// init the configuration for the benchmark
/*DbPrerequisite.initConfig((args.length > 0) ? args[0]
		: "config/mongodb.properties");*/

DbPrerequisite.initConfig("E:/foodCourtAppLogPath/propertie_Files/databasemongo.properties");

// initialize connection to mongodb and database
System.out.println("initializing connection...");
eval.initDatabase(DbPrerequisite.PATH);

// create indexes (if not existing)
System.out.println("creating indexes...");
eval.createIndexes(eval.getDb());

for (int globalRun = 1; globalRun <= DbPrerequisite.GLOBAL_RUNS; globalRun++) {

	System.out.println(String.format(
			"Starting global run %d using dataset %s", globalRun,
			DbPrerequisite.PATH));

	if (DbPrerequisite.IMPORT) {
		// data import
		System.out.println("importing data...");
		eval.importData(eval.getDataPath(), eval.getDb(), globalRun);
	}

	if (globalRun == 1) { // only necessary after the first import

		// check which stations are available in the dataset
		System.out.println("initializing available stations...");
		eval.initStations();

		// get the time range of the dataset
		System.out.println("initializing time range...");
		eval.initTimeRange();

		// get the available datatypes
		System.out.println("initializing available datatypes...");
		eval.initDataTypes();
	} else {
		System.out.println("updating available stations...");
		eval.updateStations(globalRun);
	}

	// process the benchmark
	System.out.println("processing the benchmark...");
	eval.processQueries(eval.getDb());
}

// shutdown the connection
eval.shutdown();


}
}