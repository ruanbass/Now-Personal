package br.com.ufpb.nowpersonal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.ufpb.nowpersonal.R;
import br.com.ufpb.nowpersonal.fragments.LoginFragment;
import br.com.ufpb.nowpersonal.fragments.RegisterFragment;

public class LoginRegisterActivity extends AppCompatActivity {

    /**
     * Copyright [2016] [ruan rodrigues, Ermeson Nobrega
     *Nil Allison, Igo Daniel]
     *
     *Licensed under the Apache License, Version 2.0 (the "License");
     *you may not use this file except in compliance with the License.
     *You may obtain a copy of the License at
     *
     *http://www.apache.org/licenses/LICENSE-2.0
     *
     *Unless required by applicable law or agreed to in writing, software
     *distributed under the License is distributed on an "AS IS" BASIS,
     *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     *See the License for the specific language governing permissions and
     *limitations under the License.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        boolean register = false;

        if(getIntent().getExtras() != null){
            int valor = getIntent().getExtras().getInt("button");
            register = valor != 0;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        if(register){
            mViewPager.setCurrentItem(1);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_white_1000));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent intent = new Intent(this, SlideActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = {"Login", "Cadastro"};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            if(position == 0){
                fragment = new LoginFragment();
            }else if(position == 1){
                fragment = new RegisterFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
