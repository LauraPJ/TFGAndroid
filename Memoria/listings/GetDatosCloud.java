public void getDatosFirebase() {
    Firebase myfirebase = new Firebase("https://pateostf.firebaseio.com/rutas");    
    
    ... // Instanciacion del ProgressDialog y declaracion de variables 
    
    myfirebase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
	  HashMap<String, Object> _rutas = new HashMap<String, Object>();
	  _rutas = (HashMap<String, Object>) snapshot.getValue();
	  for (Object key : _rutas.keySet() ) {
	      HashMap<String, Object> o = (HashMap<String, Object>) _rutas.get(key);
	      String nombre = (String)o.get("nombreSendero");
	      String descrp = (String)o.get("descSendero");
	      ArrayList<Object> puntos = (ArrayList<Object>)o.get("puntos");

	      Punto[] ps = new Punto[puntos.size()];
	      for (int i = 0; i < puntos.size(); i++) {
		  Punto p = new Punto();
		  HashMap<String, Object> aux = (HashMap<String, Object>)puntos.get(i);;
		  p.setLatitud((Double)aux.get("latitud"));
		  p.setLongitud((Double)aux.get("longitud"));
		  p.setElevacion((Double)aux.get("elevacion"));
		  p.setTiempo((String)aux.get("tiempo"));
		  ps[i] = p;
	      }
	      GPXData gpx = new GPXData(nombre, descrp, ps);
	      rutas.add(gpx);
	  }
	      
	  DBHandler db = new DBHandler(contexto);
	  for (int i = 0; i < rutas.size(); i++) {
	      db.insertRapidoCloud(rutas.get(i));
	  }

	  _ringProgressDialog.dismiss();
      }
      
      ... //Definicion del metodo onCancelled()
    });
}