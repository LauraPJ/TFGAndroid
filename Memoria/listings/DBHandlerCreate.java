public void onCreate(SQLiteDatabase db) {
    String CREATE_SENDEROS_TABLE = "CREATE TABLE " +
            TABLE_SENDEROS + "("
            + COLUMN_NOMBRE_SENDERO  + " TEXT,"
            + COLUMN_DESCRIPCION     + " TEXT,"
            + COLUMN_LAT_INI         + " REAL,"
            + COLUMN_LON_INI         + " REAL,"
            + COLUMN_ALT_MIN         + " REAL,"
            + COLUMN_ALT_MAX         + " REAL,"
            + COLUMN_PER_ALT         + " REAL,"
            + COLUMN_GAN_ALT         + " REAL,"
            + COLUMN_DISTANCIA       + " REAL,"
            + COLUMN_SUBIDO          + " TEXT,"
            + COLUMN_NUBE            + " TEXT,"
            + "PRIMARY KEY (" + COLUMN_NOMBRE_SENDERO + ", " + COLUMN_NUBE + ")) ";

    String CREATE_PUNTOS_TABLE = "CREATE TABLE " +
            TABLE_PUNTOS             + "("
            + COLUMN_NOMBRE_SENDERO  + " TEXT NOT NULL,"
            + COLUMN_ORDEN           + " INTEGER NOT NULL,"
            + COLUMN_LATITUD         + " REAL, "
            + COLUMN_LONGITUD        + " REAL, "
            + COLUMN_ELEVACION       + " REAL, "
            + COLUMN_TIEMPO          + " TEXT, "
            + COLUMN_DISTANCIA       + " REAL, "
            + COLUMN_NUBE            + " TEXT, "
            + "PRIMARY KEY (" + COLUMN_NOMBRE_SENDERO + ", " + COLUMN_ORDEN + ", " + COLUMN_NUBE + ") "
            + "FOREIGN KEY (" + COLUMN_NOMBRE_SENDERO + ") REFERENCES " + TABLE_SENDEROS + "(" + COLUMN_NOMBRE_SENDERO +")"
            + ")";

    db.execSQL(CREATE_SENDEROS_TABLE);
    db.execSQL(CREATE_PUNTOS_TABLE);
}