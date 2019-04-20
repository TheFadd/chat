package com.example.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chat.adapter.BlurbMessage;
import com.example.chat.adapter.FriendMessage;
import com.example.chat.adapter.MessageAdapter;
import com.example.chat.adapter.MessageRecyclerCell;
import com.example.chat.adapter.OwnMessage;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private MessageAdapter adapter;
    private RecyclerView recyclerView;
    private RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParams();
        initRewardedVideoAd();
    }

    private void initParams(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter(this, this::checkListener);
        recyclerView.setAdapter(adapter);
        List<Message> list = GeneralUtils.getFakeData();
        adapter.swapData(createDummyList(list));
    }

    private void initRewardedVideoAd(){
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    private List<MessageRecyclerCell> createDummyList(List<Message> list) {
        List<MessageRecyclerCell> items = new ArrayList<>();
        for (Message message : list) {
            if(message.getUserId() == 1){
                items.add(new OwnMessage(message));
            }else if(message.getUserId() == 2){
                items.add(new FriendMessage(message));
            }else if(message.getUserId()==3){
                items.add(new BlurbMessage(message));
            }
        }
        return items;
    }

    private void checkListener(int id) {
        if (id == 1) {
            Toast.makeText(this, "CALLBACK TO Activity",
                    Toast.LENGTH_SHORT).show();
        }else if(id ==3){
            if(rewardedVideoAd.isLoaded()){
                rewardedVideoAd.show();
            }
        }
    }

    private void loadRewardedVideoAd(){
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }
    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "Loaded",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
