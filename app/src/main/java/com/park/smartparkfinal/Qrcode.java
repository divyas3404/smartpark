package com.park.smartparkfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qrcode extends AppCompatActivity {
    TextView t1;
    ImageView QR;
    Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        t1 =(TextView)findViewById(R.id.id);
        QR= (ImageView)findViewById(R.id.qr);
        generate =(Button)findViewById(R.id.back_1);



        String data = getIntent().getExtras().getString("spot");
        String data1 = getIntent().getExtras().getString("hours");

        String finaloutput = "Spot Number   "+ data+ " Number of Hours   "+ data1+ "";
        t1.setText(finaloutput);


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    try{

                        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                        BitMatrix bitMatrix=multiFormatWriter.encode(finaloutput, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        QR.setImageBitmap(bitmap);



                    }catch (WriterException e){
                        e.printStackTrace();
                    }


            }
        });
    }
}