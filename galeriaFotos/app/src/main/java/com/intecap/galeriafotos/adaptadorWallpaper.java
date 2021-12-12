package com.intecap.galeriafotos;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class adaptadorWallpaper extends BaseAdapter {
    //Constructor
    private Context wallPaperContexto;
    public int[] wallpaperArreglo = {R.drawable.minimal1, R.drawable.minimal2, R.drawable.minimal4, R.drawable.minimal5, R.drawable.minimal6};
    public adaptadorWallpaper(Context wallPaperContexto)
    {
        this.wallPaperContexto = wallPaperContexto;
    }
    @Override
    public int getCount() {
        return wallpaperArreglo.length;
    }

    @Override
    public Object getItem(int position) {
        return wallpaperArreglo[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView visorWallpaper = new ImageView(wallPaperContexto);
        visorWallpaper.setBackgroundResource(wallpaperArreglo[position]);
        visorWallpaper.setScaleType(ImageView.ScaleType.CENTER_CROP);
        visorWallpaper.setLayoutParams(new ViewGroup.LayoutParams(340, 340));
        return visorWallpaper;
    }
}
