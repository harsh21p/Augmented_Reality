package com.hny.ar2;

import android.Manifest;
import android.animation.Animator;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class arClass extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private ArFragment arFragment;
    private ModelAnimator modelAnimator;
    private Uri model;
    Button capture;
    Button kick;
    Button boxing;
    private int i;
    private VideoRecorder videoRecord=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_class);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

         String userNameString = getIntent().getStringExtra("phone");
         String phoneNoString= getIntent().getStringExtra("users");

         capture=findViewById(R.id.animatebtn);
         kick=findViewById(R.id.kick);
         boxing=findViewById(R.id.boxing);


        userHelper userHelper = new userHelper(userNameString,phoneNoString);
        reference.child(phoneNoString).setValue(userHelper);

        arFragment=(ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        model = Uri.parse("model_fight.sfb");
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            createModel(hitResult.createAnchor(),arFragment);
        });

        capture.setOnClickListener(view -> recording());

    }


    private void createModel(Anchor anchor, ArFragment arFragment) {

        ModelRenderable
                .builder()
                .setSource(this,model)
                .build()
                .thenAccept(modelRenderable ->{

                    AnchorNode anchorNode=new AnchorNode(anchor);
                    SkeletonNode skeletonNode=new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);

                    arFragment.getArSceneView().getScene().addChild(anchorNode);

                    animateModel1(modelRenderable);

                    kick.setOnClickListener(view -> animateModel2(modelRenderable));
                    boxing.setOnClickListener(view -> animateModel(modelRenderable));

                });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }


    private void recording(){
        if(videoRecord==null){

            videoRecord=new VideoRecorder();
            videoRecord.setSceneView(arFragment.getArSceneView());
            int oriantation = getResources().getConfiguration().orientation;
            videoRecord.setVideoQuality(CamcorderProfile.QUALITY_HIGH,oriantation);

        }
        boolean isRecording = videoRecord.onToggleRecord();
        if(isRecording){
            Toast.makeText(this,"Started Recording",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Stopped Recording",Toast.LENGTH_SHORT).show();
        }
    }
    private void animateModel(ModelRenderable modelRenderable) {

        if(modelAnimator!=null && modelAnimator.isRunning())modelAnimator.end();
        AnimationData animationData = modelRenderable.getAnimationData(0);
        modelAnimator =new ModelAnimator(animationData,modelRenderable);
        modelAnimator.start();


    }

    private void animateModel1(ModelRenderable modelRenderable) {

        if(modelAnimator!=null && modelAnimator.isRunning())modelAnimator.end();
        AnimationData animationData = modelRenderable.getAnimationData(1);
        modelAnimator =new ModelAnimator(animationData,modelRenderable);
        modelAnimator.start();

    }
    private void animateModel2(ModelRenderable modelRenderable) {

        if(modelAnimator!=null && modelAnimator.isRunning())modelAnimator.end();
        AnimationData animationData = modelRenderable.getAnimationData(2);
        modelAnimator =new ModelAnimator(animationData,modelRenderable);
        modelAnimator.start();

    }



}