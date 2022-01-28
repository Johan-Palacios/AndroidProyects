package com.intecap.carrosapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

public class adapter extends RecyclerView.Adapter<adapter.CarViewHoder> {
    ArrayList<methodCar> listadoCarros;

    public adapter(ArrayList<methodCar> listadoCarros)
    {
        this.listadoCarros =listadoCarros;
    }
    @NonNull
    @Override
    public adapter.CarViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_car, null, false);
        return new CarViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.CarViewHoder holder, int position) {
        holder.vistaMarca.setText(listadoCarros.get(position).getMarca());
        holder.vistaLinea.setText(listadoCarros.get(position).getLinea());
        holder.vistaModelo.setText(listadoCarros.get(position).getModelo());
        Bitmap path = getBitmap(listadoCarros.get(position).getFoto());
        holder.vImage.setImageBitmap(path);

    }

    @Override
    public int getItemCount() {
        return listadoCarros.size();
    }

    public class CarViewHoder extends RecyclerView.ViewHolder
    {
        TextView vistaMarca, vistaLinea, vistaModelo;
        ImageView vImage;
        public CarViewHoder(@NonNull View itemView) {
            super(itemView);
            vistaMarca = itemView.findViewById(R.id.listMarca);
            vistaLinea = itemView.findViewById(R.id.listLinea);
            vistaModelo = itemView.findViewById(R.id.listModelo);
            vImage = itemView.findViewById(R.id.listLottieImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context pantVizualizar = v.getContext();
                    Intent i = new Intent(pantVizualizar, verCarIndividual.class);
                    i.putExtra("codigoCar", listadoCarros.get(getAdapterPosition()).getId());
                    pantVizualizar.startActivity(i);
                }
            });
        }
        public void animation(LottieAnimationView image, int Animation)
        {
            image.setAnimation(Animation);
            image.setRepeatCount(90);
            image.playAnimation();
        }
    }
    public static Bitmap getBitmap(String filePath) {

        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        Boolean scaleByHeight = Math.abs(options.outHeight - 100) >= Math
                .abs(options.outWidth - 100);
        if (options.outHeight * options.outWidth * 2 >= 16384) {
            double sampleSize = scaleByHeight
                    ? options.outHeight / 100
                    : options.outWidth / 100;
            options.inSampleSize =
                    (int) Math.pow(2d, Math.floor(
                            Math.log(sampleSize) / Math.log(2d)));
        }
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[512];
        Bitmap output = BitmapFactory.decodeFile(filePath, options);
        return output;
    }
    public static Bitmap rotate(Bitmap bitmap, int rotation) {

        int targetWidth = bitmap.getWidth();
        int targetHeight = bitmap.getHeight();

        if (rotation == 90 || rotation == 270) {
            targetHeight = bitmap.getWidth();
            targetWidth = bitmap.getHeight();
        }

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        Matrix matrix = new Matrix();
        matrix.setRotate(rotation, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, new Paint());

        bitmap.recycle();
        return targetBitmap;
    }
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }
    public static Bitmap cropCenter(Bitmap bitmap) {

        int minSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
        int diffSize = Math.abs(bitmap.getWidth() - bitmap.getHeight());

        Bitmap targetBitmap;
        targetBitmap = Bitmap.createBitmap(minSize, minSize, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Matrix matrix = new Matrix();
        if (bitmap.getWidth() >= bitmap.getHeight())
            matrix.setTranslate(diffSize, 0);
        else
            matrix.setTranslate(0, diffSize);

        canvas.drawBitmap(targetBitmap, new Matrix(), new Paint());

        bitmap.recycle();
        return targetBitmap;
    }
    public static Bitmap getCircularBitmap(Bitmap bitmap)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }
}
