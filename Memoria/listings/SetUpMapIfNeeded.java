private void setUpMapIfNeeded() {
    // Do a null check to confirm that we have not already instantiated the map.
    if (mMap == null) {
        // Try to obtain the map from the SupportMapFragment.
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                           .getMap();
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            mMap.setMapType(mMapTypes[indMapa]);
            setUpMap();
        }
    }
}