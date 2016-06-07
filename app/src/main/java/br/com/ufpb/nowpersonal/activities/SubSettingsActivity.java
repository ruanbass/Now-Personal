package br.com.ufpb.nowpersonal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.ufpb.nowpersonal.R;
import br.com.ufpb.nowpersonal.fragments.LoginSegurancaFragment;
import br.com.ufpb.nowpersonal.fragments.RegisterFragment;
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
public class SubSettingsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_subsettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String TAG = getIntent().getExtras().getString("TAG");

        if(TAG.equalsIgnoreCase("senha")){
            getSupportActionBar().setTitle("Login e Segurança");
            LoginSegurancaFragment fragment = new LoginSegurancaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_subsettings, fragment).commit();
        }else{
            getSupportActionBar().setTitle("Informações pessoais e privacidade");
            RegisterFragment fragment = new RegisterFragment();
            Bundle bundle = new Bundle();
            bundle.putString("TAG","info");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_subsettings, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
