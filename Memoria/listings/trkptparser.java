public String[] trkptParser(String cadena) {
    String[] puntos = cadena.split(TRKPT_MARK);
    for (int i = 0; i < puntos.length; i++) {
        String aux = cambiarCaracter(puntos[i], '"', '\'');
        puntos[i] = aux;
    }
    return puntos;
}