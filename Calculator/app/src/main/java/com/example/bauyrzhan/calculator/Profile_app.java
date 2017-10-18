package com.example.bauyrzhan.calculator;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bauyrzhan on 8/29/17.
 */

public class Profile_app extends AppCompatActivity {

    ImageView avatarPic;
    TextView fullName, id, mobilePhone, email1, email2, adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_app);
        avatarPic = (ImageView) findViewById(R.id.profile_picture);
        fullName = (TextView) findViewById(R.id.fullName);
        id = (TextView) findViewById(R.id.id);
        mobilePhone = (TextView) findViewById(R.id.mobilePhone);
        email1 = (TextView) findViewById(R.id.email1);
        email2 = (TextView) findViewById(R.id.email2);
        adress = (TextView) findViewById(R.id.adress);
    }

    public void radioClick(View view) {
        switch(view.getId()) {
            case R.id.radioButton1: {
                avatarPic.setImageResource(R.drawable.bauka);
                fullName.setText("Muratbek Bauyrzhan");
                id.setText("15BD02109");
                mobilePhone.setText("+77479899177");
                email1.setText("itman9595@yahoo.com");
                findViewById(R.id.Email2ImgView).setVisibility(View.VISIBLE);
                findViewById(R.id.email2Lbl).setVisibility(View.VISIBLE);
                email2.setVisibility(View.VISIBLE);
                findViewById(R.id.linearLayout3).setVisibility(View.VISIBLE);
                email2.setText("muratbekbauyrzhan95@gmail.com");
                adress.setText("Almaty, Kazakhstan, mcr district Kayrat, Bereke-1");
            }
                break;
            case R.id.radioButton2: {
                avatarPic.setImageResource(R.drawable.assem);
                fullName.setText("Assem Kussainova");
                id.setText("15BD02081");
                mobilePhone.setText("+77055454207");
                email1.setText("assem.kussainova@gmail.com");
                findViewById(R.id.Email2ImgView).setVisibility(View.GONE);
                findViewById(R.id.email2Lbl).setVisibility(View.GONE);
                email2.setVisibility(View.GONE);
                findViewById(R.id.linearLayout3).setVisibility(View.GONE);
                adress.setText("Almaty, Kazakhstan, Almaty Towers, 51");
            }
                break;
            case R.id.radioButton3: {
                avatarPic.setImageResource(R.drawable.dimash);
                fullName.setText("Dinmukhammed Junussov");
                id.setText("15BD02101");
                mobilePhone.setText("+60165770793");
                email1.setText("dinmukhammed.junusov@gmail.com");
                findViewById(R.id.Email2ImgView).setVisibility(View.GONE);
                findViewById(R.id.email2Lbl).setVisibility(View.GONE);
                email2.setVisibility(View.GONE);
                findViewById(R.id.linearLayout3).setVisibility(View.GONE);
                adress.setText("Astana, Kazakhstan, Kabanbay Batyr, 98");
            }
                break;
            default:
                break;
        }
    }

    /*public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }*/
}
