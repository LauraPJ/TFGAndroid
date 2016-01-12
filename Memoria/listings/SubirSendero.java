public void subirSendero(GPXData sendero) {
    Firebase myfirebase = new Firebase("https://pateostf.firebaseio.com/");
    Firebase postRef = myfirebase.child("rutas");
    postRef.push().setValue(sendero);
    DBHandler db = new DBHandler(this);
    db.setSubido(sendero.getNombreSendero());
}