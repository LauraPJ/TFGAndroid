public GPXData ParsearFichero(String path) {
    LectorFicheros lf = new LectorFicheros();
    lf.leerFichero(path);

    String sinCabecera   = extraerContenido(lf.getFicheroLeido(), TRK_MARK,    TRK_MARK_END);
    String nombreSendero = extraerContenido(sinCabecera,          NAME_MARK,   NAME_MARK_END);
    String descSendero   = extraerContenido(sinCabecera,          DESC_MARK,   DESC_MARK_END);
    String ruta          = extraerContenido(sinCabecera,          TRKSEG_MARK, TRKSEG_MARK_END);
    String[] puntos      = trkptParser(ruta);

    Punto[] gpx_puntos = new Punto[puntos.length - 1];

    for (int i = 1; i < puntos.length; i++) {
        String latitud   = extraerContenido(puntos[i], LAT_INI,   LAT_FIN);
        String longitud  = extraerContenido(puntos[i], LON_INI,   LON_FIN);
        String elevacion = extraerContenido(puntos[i], ELE_MARK,  ELE_MARK_END);
        String tiempo    = extraerContenido(puntos[i], TIME_MARK, TIME_MARK_END);
        Punto p = new Punto();
        p.setLatitud(Double.parseDouble(latitud));
        p.setLongitud(Double.parseDouble(longitud));
        p.setElevacion(Double.parseDouble(elevacion));
        p.setTiempo(tiempo);
        gpx_puntos[i - 1] = p;
    }
    return new GPXData(nombreSendero, descSendero, gpx_puntos);
}