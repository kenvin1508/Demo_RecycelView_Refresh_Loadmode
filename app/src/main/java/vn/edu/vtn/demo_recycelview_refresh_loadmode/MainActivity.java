package vn.edu.vtn.demo_recycelview_refresh_loadmode;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Random;

import vn.edu.vtn.adapter.UserAdapter;
import vn.edu.vtn.model.Message;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvUser;
    UserAdapter adapterUser;
    ArrayList<Message> listMessages;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;

    ProgressBar progressBar;
    Boolean isScrolling = false;
    int currentItem, totalItem, scrollOutItem;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();


    }

    private void addControls() {
        swipeRefreshLayout = findViewById(R.id.swipe_view);
        rvUser = findViewById(R.id.rvUser);
        progressBar = findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(this);
        listMessages = new ArrayList<>();
        adapterUser = new UserAdapter(MainActivity.this, listMessages);

        rvUser.setLayoutManager(layoutManager);
        rvUser.setItemAnimator(new DefaultItemAnimator());
        rvUser.setAdapter(adapterUser);

    }

    private void addEvents() {
        // Thực hiện kéo lên trên để fresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // thực hiện 1 tiến trình trong 1s
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        for (int i = 0; i < 5; i++) {
                            int id = random.nextInt(listMessages.size());
                            listMessages.add(0, listMessages.get(id));
                        }
                        adapterUser.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false); // tắt refresh sau khi hoàn thành
                    }
                }, 1000);
            }
        });
        fakeData();
        rvUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) // Nếu cái trạng thái là là nhấn  , giữ, cuộn
                    isScrolling = true; // Set bằng true cho biết user đang thực hiện cuộn với state trên
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = layoutManager.getChildCount(); // Số lượng item hiện tại đang xuất hiện trên màn hình
                totalItem = layoutManager.getItemCount(); // Tổng số lượng item
                scrollOutItem = layoutManager.findFirstVisibleItemPosition(); // Số lượng item đã xuất hiện khi cuộn

                if (isScrolling && (currentItem + scrollOutItem == totalItem)) {
                    isScrolling = false;
                    Log.d("AAAA", "currentItem : " + layoutManager.getChildCount()
                            + " totalItem : " + layoutManager.getItemCount() + " scrollOutItem : " + layoutManager.findFirstVisibleItemPosition());
                    loadMore();
                }
            }
        });

    }

    public void loadMore() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                    int n = listMessages.size();
                    int id = random.nextInt(n);
                    listMessages.add(n - 1, listMessages.get(id));
                }
                adapterUser.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE); // Ẩn và mất khỏi vị trí hiện tại
            }
        }, 1500);
    }

    public void fakeData() {
        listMessages.add(new Message(1, "Nghia", "Hello"));
        listMessages.add(new Message(2, "Tung", "Hello"));
        listMessages.add(new Message(3, "Nghia", "Nice to meet you !!!"));
        listMessages.add(new Message(4, "Tung", "Nice to meet you too !!!"));
        listMessages.add(new Message(5, "Nghia", "How are you ?"));
        listMessages.add(new Message(6, "Tung", "I'm good , about you ?"));
        listMessages.add(new Message(7, "Nghia", "I'm fine"));
        listMessages.add(new Message(8, "Tung", "what are you doing now ?"));
        listMessages.add(new Message(9, "Nghia", "I'm watching TV"));
        listMessages.add(new Message(10, "Tung", "What is a channel do you watching?"));
        listMessages.add(new Message(11, "Nghia", "HBO channel "));
//        listMessages.add(new Message(12, "Tung", "Ohh , that is my favorite channel too "));
//        listMessages.add(new Message(13, "Nghia", "There are many great movie on it "));
//        listMessages.add(new Message(14, "Tung", "Yes, I often watch action movie, it's very interesting"));
//        listMessages.add(new Message(15, "Nghia", "what kind of movie do you like?"));
//        listMessages.add(new Message(16, "Tung", "science fiction movies"));
//        listMessages.add(new Message(17, "Nghia", "Hey Tung, it's beautiful out today, isn't it?"));
//        listMessages.add(new Message(18, "Tung", "Yeah. I wish it would be like this everyday."));
//        listMessages.add(new Message(19, "Nghia", "Did you see the weather forecast?"));
//        listMessages.add(new Message(20, "Tung", "Unfortunately, it's supposed to start raining tomorrow night.!"));
//        listMessages.add(new Message(21, "Nghia", "I'm really happy that you could come."));
//        listMessages.add(new Message(22, "Tung", "Yeah. It's been years since we did this together"));
//        listMessages.add(new Message(23, "Nghia", "I know. It has been a long time"));
//        listMessages.add(new Message(24, "Tung", "Well, anyway, a drink to our friendship!"));
//        listMessages.add(new Message(25, "Nghia", "Cheers!"));
//
//        listMessages.add(new Message(26, "Nghia", "Hello"));
//        listMessages.add(new Message(27, "Tung", "Hello"));
//        listMessages.add(new Message(28, "Nghia", "Nice to meet you !!!"));
//        listMessages.add(new Message(29, "Tung", "Nice to meet you too !!!"));
//        listMessages.add(new Message(30, "Nghia", "How are you ?"));
//        listMessages.add(new Message(31, "Tung", "I'm good , about you ?"));
//        listMessages.add(new Message(32, "Nghia", "I'm fine"));
//        listMessages.add(new Message(33, "Tung", "what are you doing now ?"));
//        listMessages.add(new Message(34, "Nghia", "I'm watching TV"));
//        listMessages.add(new Message(35, "Tung", "What is a channel do you watching?"));
//        listMessages.add(new Message(36, "Nghia", "HBO channel "));
//        listMessages.add(new Message(37, "Tung", "Ohh , that is my favorite channel too "));
//        listMessages.add(new Message(38, "Nghia", "There are many great movie on it "));
//        listMessages.add(new Message(39, "Tung", "Yes, I often watch action movie, it's very interesting"));
//        listMessages.add(new Message(40, "Nghia", "what kind of movie do you like?"));
//        listMessages.add(new Message(41, "Tung", "science fiction movies"));
//        listMessages.add(new Message(42, "Nghia", "Hey Tung, it's beautiful out today, isn't it?"));
//        listMessages.add(new Message(43, "Tung", "Yeah. I wish it would be like this everyday."));
//        listMessages.add(new Message(44, "Nghia", "Did you see the weather forecast?"));
//        listMessages.add(new Message(45, "Tung", "Unfortunately, it's supposed to start raining tomorrow night.!"));
//        listMessages.add(new Message(46, "Nghia", "I'm really happy that you could come."));
//        listMessages.add(new Message(47, "Tung", "Yeah. It's been years since we did this together"));
//        listMessages.add(new Message(48, "Nghia", "I know. It has been a long time"));
//        listMessages.add(new Message(49, "Tung", "Well, anyway, a drink to our friendship!"));
//        listMessages.add(new Message(50, "Nghia", "Cheers!"));


    }

}
