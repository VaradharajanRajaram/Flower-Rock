package com.vail.foodcourt.service;


public class GeneralConstants {
    
    public static final String ALPHA_NUMERIC_STRING = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";

    
    
    

        /** The Constant EMPTY_STRING. */
        public static final String EMPTY_STRING = "";

        /** The Constant SINGLE_SPACE. */
        public static final String SINGLE_SPACE = " ";

        /** The Constant EQUALS. */
        public static final String EQUALS = "=";
        
        /** The Constant DOT. */
        public static final String DOT = ".";
        
        /** The Constant COMMA. */
        public static final String COMMA = ",";
        
        /** The Constant COLON. */
        public static final String COLON = ":";

        /** The Constant SEMI_COLON. */
        public static final String SEMI_COLON = ";";

        /** The Constant FRONT_SLASH. */
        public static final String FRONT_SLASH = "/";
        
        /** The Constant UNDER_SCORE. */
        public static final String UNDER_SCORE = "_";

        /** The Constant NEWLINE. */
        public static final String NEWLINE = "\n";

        /** The Constant AT_THE_RATE. */
        public static final String AT_THE_RATE = "@";

        /** The Constant FTP_PROTOCOL. */
        public static final String FTP_PROTOCOL = "ftp://";

        /** The Constant SFTP_PROTOCOL. */
        public static final String SFTP_PROTOCOL = "sftp://";

        /** The Constant FTP. */
        public static final String FTP = "FTP";

        /** The Constant SFTP. */
        public static final String SFTP = "SFTP";

        /** The Constant APPLICATION_STARTUP_STATUS. */
        public static final String APPLICATION_STARTUP_STATUS = "STARTUP_STATUS";

        /** The Constant EXECUTION_LOGGER. */
        public static final String EXECUTION_LOGGER = "EXECUTION_LOGGER";
        
        public static final String foodCourtInstallPath=System.getenv("APPDATA")+FRONT_SLASH+"foodCourtAppLogPath";
       
        public static final String $CORE_LOG=foodCourtInstallPath+FRONT_SLASH+"coreLog/core.log";
        		
        
        public static final String $DATA_RETRIVE_LOG=foodCourtInstallPath+FRONT_SLASH+"dataRetriveLog/data_rtve.log";
        
        public static final String $DB_LOG=foodCourtInstallPath+FRONT_SLASH+"dbLog/db.log";
        
        public static final String $RESUM_PROJECT_LOG=foodCourtInstallPath+FRONT_SLASH+"resumeProjectLog/start_app.log";
        
        
        public static final String $MONGO_PROPERTIEFILE=foodCourtInstallPath+FRONT_SLASH+"propertie_Files/databasemongo.properties";
        
        
    

    
}
