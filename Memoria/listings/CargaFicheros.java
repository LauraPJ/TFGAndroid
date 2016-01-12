private class FileParser extends AsyncTask<String, Void, String> {

    ... // Instanciacion del ProgressDialog, definicion de variables y metodo onPreExecute
    
    @Override
    protected String doInBackground(String... path) {
        GPXData ruta = null;
        String mensaje = "";
        try
        {
            GPXParser gpx = new GPXParser(path[0]);
            ruta = gpx.getDatos();
            changeMessage("Actualizando la base de datos...");
            DBHandler db = new DBHandler(context);
            if (!db.isGuardado(ruta)) {
                db.insertRapido(ruta);
                done = true;
                mensaje = "La base de datos fue actualizada correctamente";
            } else {
                mensaje = "Ya existe un sendero con ese nombre";
            }
        }
        catch (Exception ex)
        {
           Log.e("Ficheros", "Error al leer fichero desde memoria interna \n" + ex.getMessage());
           mensaje = "Error al leer el fichero desde memoria interna";
        }
        return mensaje;

    }

    public void changeMessage(final String mensaje) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ringProgressDialog.setMessage(mensaje);
                } catch (Exception e) {
                }
            }
        }).start();
    }
    
    ... // Definicion del metodo onPostExecute que actualiza las estructuras de datos que contienen los senderos en la clase padre
}