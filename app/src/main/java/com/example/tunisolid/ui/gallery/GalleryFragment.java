package com.example.tunisolid.ui.gallery;

import android.Manifest;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tunisolid.R;



import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;


public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private static final int PERMISSION_REQUEST = 0;

    private static final int PICK_IMAGE=100;

    ImageView imageView;
    Button btn;
    private Uri imgaURI;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        imageView = root.findViewById(R.id.ajout_img);
        root.findViewById(R.id.btn_ajout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        return root;
    }

    private void openGallery() {

        Intent galerry = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galerry,PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RESULT_OK && requestCode==PICK_IMAGE){
            imgaURI = data.getData();
            imageView.setImageURI(imgaURI);
        }
        else {imageView.setImageResource(R.drawable.add_user);}
    }


}
