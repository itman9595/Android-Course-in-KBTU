package com.example.bauyrzhan.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class PicassoGallery extends AppCompatActivity {

    @BindView(R.id.picassoEditText) EditText url;
    @BindView(R.id.picassoButton) com.beardedhen.androidbootstrap.BootstrapButton button;
    @BindView(R.id.picassoSpinner) Spinner spinner;
    @BindView(R.id.bootstrapCircleThumbnail) com.beardedhen.androidbootstrap.BootstrapCircleThumbnail picture;

    private static final String[] all_images = {
            "https://www.vanartgallery.bc.ca/the_exhibitions/images/picasso.jpg",
            "https://upload.wikimedia.org/wikipedia/en/2/23/Pablo_Picasso%2C_1901- 02%2C_Femme_au_caf%C3%A9_%28Absinthe_Drinker%29%2C_oil_on_canvas%2C_73_x_54_cm%2C_Hermita ge_Museum%2C_Sain t_Petersburg%2C_Russia.jpg",
            "http://www.artribune.com/wp-content/uploads/2012/11/Pablo-Picasso-Nature-morte-aux-tulipes.jpg",
            "http://www.wantaghschools.org/cms/lib05/NY01001016/Centricity/Domain/417/picasso-two-girls-reading.jpg"
    };

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_gallery);
        ButterKnife.bind(this);
        setImage(position);
        url.setText(all_images[position]);
    }

    @OnClick(R.id.picassoButton)
    public void buttonClick () {
        YoYo.with(Techniques.Wobble).duration(500).playOn(button);
        setImage(position);
        url.setText(all_images[position]);
    }

    @OnItemSelected(R.id.picassoSpinner)
    public void spinnerSelected (Spinner spinner, int position) {
        this.position = position;
    }

    public void setImage (int pos) {
        Picasso.with(this).load(all_images[pos]).into(picture);
    }

}
