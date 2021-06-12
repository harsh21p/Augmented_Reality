package com.hny.ar2;

import android.animation.Animator;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
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
    Button animatebtn;
    Button kick;
    Button boxing;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_class);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

         String userNameString = getIntent().getStringExtra("phone");
         String phoneNoString= getIntent().getStringExtra("users");

         animatebtn=findViewById(R.id.animatebtn);
         kick=findViewById(R.id.kick);
         boxing=findViewById(R.id.boxing);

        userHelper userHelper = new userHelper(userNameString,phoneNoString);
        reference.child(phoneNoString).setValue(userHelper);

        arFragment=(ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        model = Uri.parse("model_fight.sfb");

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            createModel(hitResult.createAnchor(),arFragment);

        });

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

                    kick.setOnClickListener(view -> animateModel2(modelRenderable));
                    boxing.setOnClickListener(view -> animateModel(modelRenderable));
                    animatebtn.setOnClickListener(view -> animateModel1(modelRenderable));

                } );
    }

    private void animateModel(ModelRenderable modelRenderable) {

        if(modelAnimator!=null && modelAnimator.isRunning())modelAnimator.end();

        int animationcount=modelRenderable.getAnimationDataCount();
        Toast.makeText(getApplicationContext(),Integer.toString(animationcount),Toast.LENGTH_SHORT).show();

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