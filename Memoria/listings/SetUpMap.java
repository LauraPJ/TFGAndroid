private void setUpMap() {
    mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
    mCamera = CameraUpdateFactory.newLatLngZoom(tfLatLng, 9);
    mMap.animateCamera(mCamera);
}