package br.com.ufpb.nowpersonal.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.com.ufpb.nowpersonal.NowPersonalApplication;
import br.com.ufpb.nowpersonal.R;
import br.com.ufpb.nowpersonal.activities.MainActivity;
import br.com.ufpb.nowpersonal.model.Usuario;
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
public class LoginFragment extends Fragment {

    private View mLoginButton;
    private View mRememberPassword;

    private EditText mMail;
    private EditText mPassword;

    private NowPersonalApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (NowPersonalApplication) getActivity().getApplicationContext();
        application.refreshUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ((TextInputLayout) view.findViewById(R.id.layout_mail_login)).setTypeface(getTypeface());
        ((TextInputLayout) view.findViewById(R.id.layout_password_login)).setTypeface(getTypeface());

        mMail = (EditText) view.findViewById(R.id.login_mail);
        mMail.setTypeface(getTypeface());

        mPassword = (EditText) view.findViewById(R.id.login_password);
        mPassword.setTypeface(getTypeface());

        mLoginButton = view.findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mRememberPassword = view.findViewById(R.id.btn_remember_pasword);
        mRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Em breve", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void attempRememberPassword() {

    }

    private void attemptLogin() {

        mMail.setError(null);
        mPassword.setError(null);

        String mail = mMail.getText().toString();
        String password = mPassword.getText().toString();

        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(mail)) {
            mMail.setError("Campo necessário");
            focusView = mMail;
            cancel = true;
        } else if (!verificarEmailValido(mail)) {
            mMail.setError("Email inválido");
            focusView = mMail;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mMail.setError("Campo necessário");
            focusView = mMail;
            cancel = true;
        } else if (!verificarSenhaValida(password)) {
            mMail.setError("Senha inválida");
            focusView = mMail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            new AsyncLogin(mail, password).execute((Void) null);
        }

    }

    private Typeface getTypeface() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_regular.ttf");
    }

    private boolean verificarEmailValido(String email) {
        return email.contains("@");
    }

    private boolean verificarSenhaValida(String senha) {
        return (senha.length() >= 4 && senha.length() <= 16);
    }

    private class AsyncLogin extends AsyncTask<Void, Void, Boolean> {

        private String email;
        private String senha;
        private Usuario usuario;
        private boolean userExists;

        public AsyncLogin(String email, String senha) {
            this.email = email;
            this.senha = senha;
            userExists = false;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            List<Usuario> usuarios = application.getUsuarios();
            for (Usuario u : usuarios) {
                if (u.getEmail().equalsIgnoreCase(email)) {
                    userExists = true;
                    usuario = u;
                    return u.getSenha().equalsIgnoreCase(senha);
                }
            }

            return userExists;
        }

        @Override
        protected void onPostExecute(Boolean sucess) {

            if (sucess) {
                usuario.setStatus(true);
                application.updateUsuario(usuario);
                application.refreshUsers();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            } else {
                if (userExists) {
                    mPassword.setError("Senha incorreta");
                    mPassword.requestFocus();
                } else {
                    mMail.setError("Usuário não existe");
                    mMail.requestFocus();
                }
            }

        }
    }

}
