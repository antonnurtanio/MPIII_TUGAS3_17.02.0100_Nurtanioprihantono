package com.example.jean.retrofitexample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jean.retrofitexample.FootbballADapter;
import com.example.jean.retrofitexample.R;
import com.example.jean.retrofitexample.model.HistoryData;
import com.example.jean.retrofitexample.model.PlayerData;
import com.example.jean.retrofitexample.presenter.FootballPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FootballView {

    private List<HistoryData> mhistoryDataList;
    RecyclerView mRecData;
    LinearLayoutManager mLayoutManager;
    FootbballADapter footballAdapter;
    FootballPresenter presenter;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            HistoryData historyData = mhistoryDataList.get(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecData = findViewById(R.id.rv_data);
        mLayoutManager = new LinearLayoutManager(this);
        presenter = new FootballPresenter(this);
        mRecData.setLayoutManager(mLayoutManager);

        presenter.getHistoryData();

    }

    @Override
    public void playerReady(List<PlayerData> data) {

    }

    @Override
    public void historyReady(List<HistoryData> data) {

        footballAdapter = new FootbballADapter(data, MainActivity.this);
        mRecData.setAdapter(footballAdapter);
        footballAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Connection Error" + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    private void showToastInLocation()
    {
        Button toastButton = (Button)findViewById(R.id.btn_history);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Custom Toast Location", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 100,100);
                toast.show();
            }
        });
    }
}
