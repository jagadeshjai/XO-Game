package com.example.xogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    int myActivePlayer = 0;
    static boolean gameState = false;
    static ArrayList<Integer> playerOne = new ArrayList();
    static ArrayList<Integer> playerTwo = new ArrayList();

    public void imageTapped(View view){
        Log.i("msg","Tapped");
        if(gameState) {
            Toast.makeText(view.getContext(), "Game Over!Press Play Again..", Toast.LENGTH_LONG).show();
            return;
        }

        ImageView image = (ImageView) view;
        while(playerOne.contains(Integer.parseInt(image.getTag().toString())) || playerTwo.contains(Integer.parseInt(image.getTag().toString())))
            return;
        if(myActivePlayer==0) {
            image.animate().rotationBy(360).setDuration(1000);
            image.setImageResource(R.drawable.cross);
            myActivePlayer = 1;
            playerOne.add(Integer.parseInt(image.getTag().toString()));
        }else{
            image.animate().rotation(360).setDuration(1000);
            image.setImageResource(R.drawable.circle);
            myActivePlayer = 0;
            playerTwo.add(Integer.parseInt(image.getTag().toString()));
        }
        if(playerOne.size()+playerTwo.size() >= 5) {
            checkWinning(view);
        }


    }
    public void playAgain(View view){

        //Step 1: Changing all the images to ic_launcher
        LinearLayout linearLayout1 = findViewById(R.id.layoutOne);
        for(int i = 0; i<linearLayout1.getChildCount(); i++){
            ((ImageView)linearLayout1.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
        }
        LinearLayout linearLayout2 = findViewById(R.id.layoutTwo);
        for(int i = 0; i<linearLayout2.getChildCount(); i++){
            ((ImageView)linearLayout2.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
        }
        LinearLayout linearLayout3 = findViewById(R.id.layoutThree);
        for(int i = 0; i<linearLayout3.getChildCount(); i++){
            ((ImageView)linearLayout3.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
        }

        //Step 2: Changing myActivePlayer to zero
        myActivePlayer = 0;

        //Step 3: clearing playerOne and playerTwo ArrayList
        playerOne.clear();
        playerTwo.clear();

        Log.i("msg","PlayerOne Size :"+playerOne.size());
        Log.i("msg","PlayerTwo Size :"+playerTwo.size());
        //playerTwo.size());

        //Step 4: make game state as false
        gameState = false;

    }

    public static void checkWinning(View view){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List leftCross = Arrays.asList(1,5,9);
        List rightCross = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(leftCross);
        winning.add(rightCross);

        int flag = 0;
        for(List l : winning) {
            if (playerOne.containsAll(l)) {
                Log.i("msg","Congratulation Player One Won!");
                Toast.makeText(view.getContext(),
                        "Congratulation Player One Won!",
                        Toast.LENGTH_SHORT).show();
                gameState = true;
                flag = 1;

            }
            //   Log.i("msg0","Congratulation Player One Won!");
            else if (playerTwo.containsAll(l)) {
                   Log.i("msg","Congratulation Player Two Won!");
                Toast.makeText(view.getContext(),
                        "Congratulation Player Two Won!",
                        Toast.LENGTH_SHORT).show();
                gameState = true;
                flag = 1;
            }
        }

        if(playerOne.size()+playerTwo.size() == 9 && flag == 0) {
            //  Log.i("msg","Its Draw..");
            Toast.makeText(view.getContext(),
                    "Its an Draw : (",
                    Toast.LENGTH_SHORT).show();
            gameState =true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}

