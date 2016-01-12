private void reload() {
        swipeListView = (SwipeListView) findViewById(R.id.example_lv_list);

        ... // Definicion de acciones dependiendo del SDK del dispositivo
        
        swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            
            ... // Definicion en blanco de eventos no controlados en PateosTF
            
            @Override
            // Cuando se selecciona un item
            public void onClickFrontView(int position) {
                Toast.makeText(SwipeListViewActivity.this, "Cargando Info", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SwipeListViewActivity.this, SenderoViewer.class);
                intent.putExtra("sendero", data.get(position));
                startActivity(intent);
                Log.d("swipe", String.format("onClickFrontView %d", position));
            }

        });

        swipeListView.setAdapter(adapter);
        SettingsManager settings = SettingsManager.getInstance();
        swipeListView.setSwipeMode(settings.getSwipeMode());
        swipeListView.setSwipeActionLeft(settings.getSwipeActionLeft());
        swipeListView.setSwipeActionRight(settings.getSwipeActionRight());
        swipeListView.setOffsetLeft(convertDpToPixel(settings.getSwipeOffsetLeft()));
        swipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
        swipeListView.setAnimationTime(settings.getSwipeAnimationTime());
        swipeListView.setSwipeOpenOnLongPress(settings.isSwipeOpenOnLongPress());
    }