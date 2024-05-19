package com.example.bandymas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bandymas.fragmentsForChatting.UsersFragment;
import com.example.bandymas.fragmentsForChatting.chatFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class messaging extends AppCompatActivity {
    private ImageButton back, add;
    private TabLayout tabChatUserLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        back = findViewById(R.id.imageButton);
        add = findViewById(R.id.message_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messaging.this, selectingUser.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messaging.this, home_page.class);
                startActivity(intent);

            }
        });
        ImageButton vector_click = findViewById(R.id.add_aJob_btn);
        vector_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(messaging.this, job_fill_form.class);
                startActivity(intent);
            }
        });
        //other toolbar buttons:
        ImageButton messg = findViewById(R.id.message_btn);
        ImageButton searchh = findViewById(R.id.search_button);
        ImageButton notificationss = findViewById(R.id.notifications_btn);

        messg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messaging.this, messaging.class);
                startActivity(intent);

            }
        });
        searchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messaging.this, searchView.class);
                startActivity(intent);
            }
        });
        //ateity pataisyti!!
        notificationss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messaging.this, notifications_.class);
                startActivity(intent);
            }
        });
        //For displaying fragments:
        tabChatUserLayout = findViewById(R.id.tabLayout);
        //TabItem first = findViewById(R.id.userTab);
        //TabItem second = findViewById(R.id.userChat);
        ViewPager2 pager2 = findViewById(R.id.viewPager2);
        //TabLayout.Tab firstTab = tabChatUserLayout.get
        PageAdapter pageAdapter = new PageAdapter(this);
        pageAdapter.addFragment(new UsersFragment(), "Users");
        pageAdapter.addFragment(new chatFragment(), "Chats");
        pager2.setAdapter(pageAdapter);
        //tabChatUserLayout.setupWithViewPager(pager2);

        new TabLayoutMediator(tabChatUserLayout, pager2, (tab, position) -> tab.setText(pageAdapter.getTitles(position))).attach();
    }
    class PageAdapter extends FragmentStateAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public PageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment, String tittle)
        {
            if (fragment !=null && tittle!= null)
            {
                fragments.add(fragment);
                titles.add(tittle);

            }
        }
        public CharSequence getTitles(int pos){
            return titles.get(pos);
        }


    }
}