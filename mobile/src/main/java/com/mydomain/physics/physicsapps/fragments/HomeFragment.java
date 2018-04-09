package com.mydomain.physics.physicsapps.fragments;



import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.mydomain.physics.physicsapps.R;

import java.io.File;


/**
 * Created by rishad on 3/4/18.
 */


public class HomeFragment extends Fragment  {



    PDFView pdfView;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);

         pdfView=(PDFView)rootView.findViewById(R.id.pdfView);

         pdfView.fromAsset("physics1.pdf")
                 .enableSwipe(true) // allows to block changing pages using swipe
                 .swipeHorizontal(false)
                 .enableDoubletap(true)
                 .password("demo1234")
                 .load();





        return rootView;
    }




}
