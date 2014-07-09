package com.vail.foodcourt.database;
import java.util.List;
import java.util.Random;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceCommand.OutputType;
import com.vail.foodcourt.service.DbPrerequisite;
import com.vail.foodcourt.service.DbPrerequisite.DataType;






public class MongoDB_Queries {

	private static Random r;

	/*
	 * Helper
	 */

	/**
	 * Returns the Unix Timestamp which is a specified number of days greater
	 * than the given one.
	 * 
	 * @param from
	 *            start unix timestamp
	 * @param days
	 *            number of days
	 * @return unix timestamp in n days
	 */
	private static Long getUpperBound(Long from, int days) {
		return from + (days * 24L * 60L * 60L * 1000L);
	}

	/**
	 * Returns a random Unix Timestamp in a given range.
	 * 
	 * @param from
	 *            lower bound
	 * @param to
	 *            upper bound
	 * @param excludeDays
	 *            if true, the defined range in config will be substracted from
	 *            the upperbound to achieve equal ranges
	 * @return timestamp in range
	 */
	private static Long getRandomInRange(Long from, Long to, boolean excludeDays) {
		if (excludeDays) {
			from -= (DbPrerequisite.DAYS * 24L * 60L * 60L * 1000L);
		}
		return from + nextLong(to - from);
	}

	/**
	 * Generates a random long value between 0 and the given upper bound. Taken
	 * from here:
	 * http://stackoverflow.com/questions/2546078/java-random-long-number
	 * -in-0-x-n-range
	 * 
	 * @param n
	 *            maximum excluded value
	 * @return
	 */
	private static long nextLong(long n) {
		long bits, val;
		do {
			bits = (r.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits - val + (n - 1) < 0L);
		return val;
	}

	public static void initRandom() {
		r = (DbPrerequisite.RANDOM_SEED == -1) ? new Random() : new Random(
				DbPrerequisite.RANDOM_SEED);
	}

	/**
	 * 1 Wieviele Einträge hat Zeitreihe XY insgesamt/im Zeitintervall
	 * [von,bis]?
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query1(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get collection
		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		String dataType = availableDataTypes.get(r.nextInt(availableDataTypes
				.size()));
		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());
		Long lowerBound = (DbPrerequisite.USE_TOTAL_RANGE) ? lowerTimeBound
				: getRandomInRange(lowerTimeBound, upperTimeBound, true);
		Long upperBound = (DbPrerequisite.USE_TOTAL_RANGE) ? upperTimeBound
				: getUpperBound(lowerBound, DbPrerequisite.DAYS);

		// range query
		BasicDBObject rangeQuery = new BasicDBObject();
		// identifier
		rangeQuery.put(DbPrerequisite.DATATYPE, dataType);
		rangeQuery.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		rangeQuery.put(DbPrerequisite.SERIAL_NO, serialNo);
		// range
		rangeQuery.put(DbPrerequisite.TIMESTAMP, new BasicDBObject("$gt",
				lowerBound).append("$lt", upperBound));

		long diff = processQuery(measCollection, rangeQuery);

		System.out.println(String.format("%s;%d;%s;%d;%d;%d",
				stationInfo.getName(), serialNo, dataType, lowerBound,
				upperBound, diff));
	}

	/**
	 * 2 Wie ist der Wert der Zeitreihe XY zum Zeitpunkt Z?
	 * 
	 * TODO: because of the random timestamps there are mostly no results found.
	 * Maybe I should chose a random timestamp based in the existing ones
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query2(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get collection
		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		String dataType = availableDataTypes.get(r.nextInt(availableDataTypes
				.size()));
		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());
		Long timestamp = getRandomInRange(lowerTimeBound, upperTimeBound, false);

		// exact match query
		BasicDBObject exactQuery = new BasicDBObject();
		// selection
		exactQuery.put(DbPrerequisite.DATATYPE, dataType.toString());
		exactQuery.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		exactQuery.put(DbPrerequisite.SERIAL_NO, serialNo);
		exactQuery.put(DbPrerequisite.TIMESTAMP, timestamp);

		// projection
		BasicDBObject projection = new BasicDBObject();
		projection.put(DbPrerequisite.VALUE, 1);

		long diff = processQuery(measCollection, exactQuery, projection);
		System.out.println(String.format("%s;%d;%s;%d;%d",
				stationInfo.getName(), serialNo, dataType, timestamp, diff));
	}

	/**
	 * 3 Wie sind die Werte der Zeitreihe XY im Zeitintervall [von,bis]?
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query3(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get random time series

		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		String dataType = availableDataTypes.get(r.nextInt(availableDataTypes
				.size()));
		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());
		Long lowerBound = (DbPrerequisite.USE_TOTAL_RANGE) ? lowerTimeBound
				: getRandomInRange(lowerTimeBound, upperTimeBound, true);
		Long upperBound = (DbPrerequisite.USE_TOTAL_RANGE) ? upperTimeBound
				: getUpperBound(lowerBound,DbPrerequisite.DAYS);

		// build query

		BasicDBObject rangeQuery = new BasicDBObject();
		// identifier
		rangeQuery.put(DbPrerequisite.DATATYPE, dataType);
		rangeQuery.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		rangeQuery.put(DbPrerequisite.SERIAL_NO, serialNo);
		// range
		rangeQuery.put(DbPrerequisite.TIMESTAMP, new BasicDBObject("$gt",
				lowerBound).append("$lt", upperBound));

		// projection
		BasicDBObject projection = new BasicDBObject();
		projection.put(DbPrerequisite.VALUE, 1);

		// run
		long diff = processQuery(measCollection, rangeQuery, projection);

		System.out.println(String.format("%s;%d;%s;%d;%d;%d",
				stationInfo.getName(), serialNo, dataType, lowerBound,
				upperBound, diff));
	}

	/**
	 * 4 Wie ist der Zeitpunkt des ältesten/neuesten Eintrags in Zeitreihe XY?
	 * (neuester)
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query4(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get random time series

		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		String dataType = availableDataTypes.get(r.nextInt(availableDataTypes
				.size()));
		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());

		// build Query

		BasicDBObject query = new BasicDBObject();
		query.put(DbPrerequisite.DATATYPE, dataType);
		query.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		query.put(DbPrerequisite.SERIAL_NO, serialNo);

		// projection
		BasicDBObject projection = new BasicDBObject();
		projection.put(DbPrerequisite.TIMESTAMP, 1);

		// run
		long start = System.currentTimeMillis();
		long result = (Long) measCollection.find(query, projection)
				.sort(new BasicDBObject(DbPrerequisite.TIMESTAMP, 1)).limit(1)
				.next().get(DbPrerequisite.TIMESTAMP);
		long diff = System.currentTimeMillis() - start;

		System.out.println(String.format("%s;%d;%s;%d;%d",
				stationInfo.getName(), serialNo, dataType, result, diff));
	}

	/**
	 * 5 Wie ist der maximale/minimale/durchschnittliche Wert der Zeitreihe XY
	 * im Zeitintervall [von,bis]?
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query5(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get random time series

		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		String dataType = availableDataTypes.get(r.nextInt(availableDataTypes
				.size()));
		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());
		Long lowerBound = (DbPrerequisite.USE_TOTAL_RANGE) ? lowerTimeBound
				: getRandomInRange(lowerTimeBound, upperTimeBound, true);
		Long upperBound = (DbPrerequisite.USE_TOTAL_RANGE) ? upperTimeBound
				: getUpperBound(lowerBound, DbPrerequisite.DAYS);

		// configure Map Reduce

		String map = "function() { emit(this." + DbPrerequisite.STATION_ID
				+ ", {total:this." + DbPrerequisite.VALUE
				+ ", count:1, avg:0, min:this." + DbPrerequisite.VALUE
				+ ", max:this." + DbPrerequisite.VALUE + "});}";

		String reduce = "function(key, values) {"
				+ "var r = {total:0, count:0, avg:0, min:0, max:0};"
				+ " if(values.length > 0) {" + " r.min = values[0].min;"
				+ " r.max = values[0].max;" + " }"
				+ " values.forEach(function(v) {" + " r.total += v.total;"
				+ " r.count += v.count;" + " if(v.min < r.min) {"
				+ " r.min = v.min;" + " }" + " if(v.max > r.max) {"
				+ " r.max = v.max;" + " }" + "});" + " return r;" + " }";

		String finalize = "function(k, r) { if(r.count > 0) r.avg = r.total / r.count; return r; }";

		DBObject query = new BasicDBObject();
		query.put(DbPrerequisite.DATATYPE, dataType);
		query.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		query.put(DbPrerequisite.SERIAL_NO, serialNo);
		// range
		query.put(DbPrerequisite.TIMESTAMP,
				new BasicDBObject("$gt", lowerBound).append("$lt", upperBound));

		MapReduceCommand mrCommand = new MapReduceCommand(measCollection, map,
				reduce, null, OutputType.INLINE, query);
		mrCommand.setFinalize(finalize);

		// run
		long start = System.currentTimeMillis();
		measCollection.mapReduce(mrCommand);
		long diff = System.currentTimeMillis() - start;

		System.out.println(String.format("%s;%d;%s;%d;%d;%d",
				stationInfo.getName(), serialNo, dataType, lowerBound,
				upperBound, diff));
	}

	/**
	 * 6 Wie ist der Verlauf des Wirkungsgrades für den Wechselrichter XY im
	 * Zeitintervall [von,bis]? (Wirkungsgrad:=PAC/(Summe PDC aller Strings))
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query6(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get random time series

		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());
		Long lowerBound = (DbPrerequisite.USE_TOTAL_RANGE) ? lowerTimeBound
				: getRandomInRange(lowerTimeBound, upperTimeBound, true);
		Long upperBound = (DbPrerequisite.USE_TOTAL_RANGE) ? upperTimeBound
				: getUpperBound(lowerBound, DbPrerequisite.DAYS);

		// configure Map Reduce

		String map = "function() { var r = {pac:0, total_pdc:0}; if(this."
				+ DbPrerequisite.DATATYPE + " == 'pac') { r.pac = this."
				+ DbPrerequisite.VALUE + "; } else { r.total_pdc = this."
				+ DbPrerequisite.VALUE + ";} emit(this."
				+ DbPrerequisite.TIMESTAMP + ", r);}";

		String reduce = "function(key, values) { var r = {pac : 0, total_pdc:0}; values.forEach(function(v) { r.pac += v.pac; r.total_pdc += v.total_pdc;}); return r;}";

		String finalize = "function(k, r) { if(r.total_pdc > 0) { return {timestamp : k, wirkungsgrad : r.pac / r.total_pdc }; } else { return {timestamp : k, wirkungsgrad : 0 };}}";

		DBObject query = new BasicDBObject();
		// PDC or PAC
		BasicDBList list = new BasicDBList();
		list.add(DataType.PDC.toString());
		list.add(DataType.PAC.toString());

		query.put(DbPrerequisite.DATATYPE, new BasicDBObject("$in", list));
		query.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		query.put(DbPrerequisite.SERIAL_NO, serialNo);
		// range
		query.put(DbPrerequisite.TIMESTAMP,
				new BasicDBObject("$gt", lowerBound).append("$lt", upperBound));

		MapReduceCommand mrCommand = new MapReduceCommand(measCollection, map,
				reduce, null, OutputType.INLINE, query);
		mrCommand.setFinalize(finalize);

		// run

		long start = System.currentTimeMillis();
		measCollection.mapReduce(mrCommand);
		long diff = System.currentTimeMillis() - start;

		System.out.println(String.format("%s;%d;%d;%d;%d",
				stationInfo.getName(), serialNo, lowerBound, upperBound, diff));
	}

	/**
	 * 7 An welchen Tagen hat Zeitreihe XY den Schwellenwert Z
	 * über-/unterschritten? (Beispiel: Wann war der Tagesertrag unter 100?)
	 * 
	 * This is a combination of map reduce and a following JSON Query. The map
	 * reduce resulting table is dropped after the benchmark completed.
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query7(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get random time series

		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());

		// configure Map Reduce

		String map = "function() { var r = {count:1, total:this."
				+ DbPrerequisite.VALUE + ", avg:0, timestamp:this."
				+ DbPrerequisite.TIMESTAMP + "}; var day = Math.floor(this."
				+ DbPrerequisite.TIMESTAMP
				+ " / 1000 / 60 / 60 / 24);	emit(day, r);}";

		String reduce = "function(key, values) { var r = {count:0, total:0, avg:0, timestamp:0}; values.forEach(function(v) { r.total += v.total; r.count += v.count; r.timestamp = v.timestamp; }); return r;}";

		String finalize = "function(k, r) { var date = new Date(r.timestamp); var dateString = date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear(); if(r.count > 0) { r.avg = r.total / r.count; } return {date : dateString, res : r};	}";

		String outputCollection = "temp";

		DBObject query = new BasicDBObject();
		query.put(DbPrerequisite.DATATYPE, DataType.GAIN.toString());
		query.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		query.put(DbPrerequisite.SERIAL_NO, serialNo);

		MapReduceCommand mrCommand = new MapReduceCommand(measCollection, map,
				reduce, outputCollection, OutputType.REPLACE, query);
		mrCommand.setFinalize(finalize);

		// query the map reduce results for the relevant data

		// get the temp collection
		DBCollection temp = mongoDB.getCollection(outputCollection);

		DBObject query2 = new BasicDBObject("value.res.total",
				new BasicDBObject("$lt", 100000));
		DBObject projection = new BasicDBObject("value.date", 1);

		// run

		long start = System.currentTimeMillis();
		measCollection.mapReduce(mrCommand);
		temp.find(query2, projection).count();
		long diff = System.currentTimeMillis() - start;

		// drop temporary data

		temp.drop();

		System.out.println(String.format("%s;%d;%d", stationInfo.getName(),
				serialNo, diff));
	}

	/**
	 * 8 Wie groß ist die erzeugte Leistung von Wechselrichter X
	 * durchschnittlich pro Temperaturstufe? (d.h. Durchschnitt von PAC je Wert
	 * der Temperaturzeitreihe)
	 * 
	 * This query has 2 map reduce phases: Phase 1 groups PAC and TEMP by
	 * timestamp Phase 2 groups the docs fom Phase 1 by TEMP and calculates the
	 * average PAC
	 * 
	 * @param mongoDB
	 * @param availableStations
	 * @param availableDataTypes
	 * @param lowerTimeBound
	 * @param upperTimeBound
	 */
	public static void query8(DB mongoDB, List<StationInfo> availableStations,
			List<String> availableDataTypes, Long lowerTimeBound,
			Long upperTimeBound) {
		// get random time series

		DBCollection measCollection = mongoDB
				.getCollection(DbPrerequisite.COLLECTION_MEASURINGS);

		StationInfo stationInfo = availableStations.get(r
				.nextInt(availableStations.size()));
		int serialNo = r.nextInt(stationInfo.getWrCount());

		// configure Map Reduce Phase 1

		String map = "function() { var r = { temp : 0, pac : 0 }; if(this."
				+ DbPrerequisite.DATATYPE + " == 'temp') r.temp = this."
				+ DbPrerequisite.VALUE + "; else r.pac = this."
				+ DbPrerequisite.VALUE + "; emit(this."
				+ DbPrerequisite.TIMESTAMP + ", r);}";

		String reduce = "function(key, values) { var r = { temp : 0, pac : 0 }; values.forEach(function(v) {if(v.temp > 0) r.temp = v.temp; else r.pac = v.pac; }); return r; }";

		String outputCollection = "temp";

		DBObject query = new BasicDBObject();
		// TEMP or PAC
		BasicDBList list = new BasicDBList();
		list.add(DataType.TEMP.toString());
		list.add(DataType.PAC.toString());

		query.put(DbPrerequisite.DATATYPE, new BasicDBObject("$in", list));
		query.put(DbPrerequisite.STATION_ID, stationInfo.getName());
		query.put(DbPrerequisite.SERIAL_NO, serialNo);

		MapReduceCommand mrCommand1 = new MapReduceCommand(measCollection, map,
				reduce, outputCollection, OutputType.REPLACE, query);

		// configure Map Reduce Phase 2

		DBCollection tempCollection = mongoDB.getCollection(outputCollection);

		String map2 = "function() { var r = { temp : 0, pac : 0 }; if(this."
				+ DbPrerequisite.DATATYPE + " == 'temp') r.temp = this."
				+ DbPrerequisite.VALUE + "; else r.pac = this."
				+ DbPrerequisite.VALUE + "; emit(this."
				+ DbPrerequisite.TIMESTAMP + ", r);}";

		String reduce2 = "function(key, values) { var r = { temp : 0, pac : 0 }; values.forEach(function(v) {if(v.temp > 0) r.temp = v.temp; else r.pac = v.pac; }); return r; }";

		String finalize = "function(key, value) { if(value.count > 0) return{temp : key, avg_pac : value.total_pac / value.count };}";

		MapReduceCommand mrCommand2 = new MapReduceCommand(tempCollection,
				map2, reduce2, null, OutputType.INLINE, query);
		mrCommand2.setFinalize(finalize);

		// run

		long start = System.currentTimeMillis();
		measCollection.mapReduce(mrCommand1);
		tempCollection.mapReduce(mrCommand2);
		long diff = System.currentTimeMillis() - start;

		// drop temporary data

		tempCollection.drop();

		System.out.println(String.format("%s;%d;%d", stationInfo.getName(),
				serialNo, diff));
	}

	/**
	 * Creates an index on the given attribute in the given collection.
	 * 
	 * @param mongoDB
	 * @param collectionName
	 * @param indexName
	 */
	public static void createIndex(DB mongoDB, String collectionName,
			DBObject index) {
		// get collection
		DBCollection collection = mongoDB.getCollection(collectionName);

		long start = System.currentTimeMillis();
		collection.ensureIndex(index);
		long diff = System.currentTimeMillis() - start;

		System.out.println(String.format("%s;%d", index, diff));
	}

	/**
	 * Drops an index from an attribute at a given collection
	 * 
	 * @param mongoDB
	 * @param collectionName
	 * @param indexName
	 */
	public static void dropIndex(DB mongoDB, String collectionName,
			DBObject index) {
		// get collection
		DBCollection collection = mongoDB.getCollection(collectionName);

		long start = System.currentTimeMillis();
		collection.dropIndex(index);
		long diff = System.currentTimeMillis() - start;

		System.out.println(String.format("%s;%d", index, diff));
	}

	private static long processQuery(DBCollection coll, BasicDBObject query) {
		return processQuery(coll, query, null);
	}

	/**
	 * Processes a given query and measures the duration.
	 * 
	 * @param coll
	 *            the collection to perform the query on
	 * @param query
	 *            the query
	 * @param skips
	 *            number of warmup runs
	 * @param runs
	 *            number of runs
	 * @return
	 */
	private static long processQuery(DBCollection coll, BasicDBObject query,
			BasicDBObject fields) {
		long start = System.currentTimeMillis();
		coll.find(query, fields).count();
		return System.currentTimeMillis() - start;
	}
}