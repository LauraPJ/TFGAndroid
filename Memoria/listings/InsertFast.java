public void insertRapido(GPXData gpx) {
    SQLiteDatabase db = this.getWritableDatabase();

    String insert_send_ini = "INSERT INTO " + TABLE_SENDEROS + " VALUES (";

    String descripcion_prev = cambiarCaracter(gpx.getDescSendero(), '\'', '.');
    
    String descripcion = descripcion_prev;
    String insert_send = insert_send_ini
            + "'" + gpx.getNombreSendero()     + "', "
            + "'" + descripcion                    + "', "
            + gpx.getPuntos()[0].getLatitud()  + ", "
            + gpx.getPuntos()[0].getLongitud()  + ", "
            + gpx.getAlturaMinima() + ", "
            + gpx.getAlturaMaxima() + ", "
            + gpx.getPerdidaAltitud() + ", "
            + gpx.getGananciaAltitud() + ", "
            + gpx.getDistancia() + ", "
            + "'" + "NO" + "', "
            + "'" + "NO" + "')";
    
    db.execSQL(insert_send);

    String insert_punt = "INSERT INTO " + TABLE_PUNTOS   + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    SQLiteStatement statement = db.compileStatement(insert_punt);
    db.beginTransaction();
    for (int j = 0; j < gpx.getPuntos().length; j++) {
        statement.clearBindings();
        statement.bindString(1, gpx.getNombreSendero());
        statement.bindLong(2, j);
        statement.bindDouble(3, gpx.getPuntos()[j].getLatitud());
        statement.bindDouble(4, gpx.getPuntos()[j].getLongitud());
        statement.bindDouble(5, gpx.getPuntos()[j].getElevacion());
        statement.bindString(6, gpx.getPuntos()[j].getTiempo());
        statement.bindDouble(7, gpx.getPuntos()[j].getDistancia());
        statement.bindString(8, "NO");
        statement.execute();
    }
    db.setTransactionSuccessful();
    db.endTransaction();
}