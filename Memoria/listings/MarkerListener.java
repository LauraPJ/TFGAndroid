@Override
public boolean onMarkerClick(Marker marker) {
    // TODO Auto-generated method stub
    for (int i = 0; i < Marcadores.size(); i++) {
        if (marker.equals(Marcadores.get(i).getMarcador())) {
            // Si no hay nada pintado o se hace click sobre otro
            // sendero (aunque haya uno pintado), se pinta el sendero
            if ((mapasinpintar) || (pintado != i)){
                dialogsinmostrar = true;
                pintarMarcador(i);
                show_dialog = true;
                mapasinpintar = false;
                ruta_showed = rutas.get(i);
                return true;
            }
            // Si se ha seleccionado un sendero que esta pintado se muestra
            // la informacion del sendero
            else if (dialogsinmostrar) {
                dialogsinmostrar = false;
                FragmentManager fragmentManager = getFragmentManager();
                InfoDialog dialogo = new InfoDialog();
                dialogo.setCancelable(true);
                dialogo.show(fragmentManager, "tagAlerta");
                return true;
            }
            // Sino se llama a pintarMarcador(i) que lo despinta, y se
            // inician de nuevo las estructuras de datos
            else {
                pintarMarcador(i);
                ruta_showed = null;
                mapasinpintar = true;
                dialogsinmostrar = true;
                return true;
            }
            
        }
    }
    return false;
}