package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // this is variable
    ImageView _Slot1,_Slot2,_Slot3;
    Button _btnStart;
    boolean isplay = false;
    private static int [] _imgs = {R.drawable.slot1, R.drawable.slot2,R.drawable.slot3,
            R.drawable.slot4,R.drawable.slot5,R.drawable.slotbar};

   AsyncSlotTask _SlotAsyn1,_SlotAsyn2,_SlotAsyn3;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            _Slot1 = findViewById(R.id.idSlot1);
            _Slot2 = findViewById(R.id.idSlot2);
            _Slot3 = findViewById(R.id.idSlot3);

            _Slot1.setImageResource(R.drawable.slotbar);
            _Slot2.setImageResource(R.drawable.slotbar);
            _Slot3.setImageResource(R.drawable.slotbar);

            _btnStart = findViewById(R.id.button);
            _btnStart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        if (v.getId()==_btnStart.getId()){
            if(!isplay){

                _SlotAsyn1 = new AsyncSlotTask();
                _SlotAsyn2 = new AsyncSlotTask();
                _SlotAsyn3 = new AsyncSlotTask();

                _SlotAsyn1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_Slot1 );
                _SlotAsyn2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_Slot2 );
                _SlotAsyn3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_Slot3 );

                _btnStart.setText("STOP");
                isplay = !isplay;
            }
            else {
                _SlotAsyn1._Play = false;
                _SlotAsyn2._Play = false;
                _SlotAsyn3._Play = false;
                _btnStart.setText("PLAY");
                isplay = !isplay;
            }
        }
    }

    private class AsyncSlotTask extends AsyncTask<ImageView, Integer, Boolean> {

            ImageView _Slotimg;
            Random random = new Random();
            public boolean _Play =true;

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        public AsyncSlotTask(){
            _Play = true;
        }

        @Override
        protected Boolean doInBackground(ImageView... imgs) {
            _Slotimg = imgs[0];
            int a =0;
            while (_Play){
                int i = random.nextInt(6);
                publishProgress(i);

            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
            return !_Play;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            _Slotimg.setImageResource(_imgs[values[0]]);
        }
    }
}
