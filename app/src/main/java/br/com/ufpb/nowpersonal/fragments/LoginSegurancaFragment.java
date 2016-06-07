package br.com.ufpb.nowpersonal.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

import br.com.ufpb.nowpersonal.NowPersonalApplication;
import br.com.ufpb.nowpersonal.R;
import br.com.ufpb.nowpersonal.activities.SlideActivity;
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
public class LoginSegurancaFragment extends Fragment {

    private View alterarSenhaItem;
    private View alterarPinItem;
    private View removerConta;

    private View containerSenha;

    private EditText mNewSenha;
    private EditText mConfirmarSenha;

    private TextView novoPin;

    private NowPersonalApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (NowPersonalApplication) getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_seguranca, container, false);

        ((TextInputLayout) view.findViewById(R.id.layout_new_senha)).setTypeface(getTypeface());
        ((TextInputLayout) view.findViewById(R.id.layout_confirmar_senha)).setTypeface(getTypeface());

        mNewSenha = (EditText) view.findViewById(R.id.new_senha);
        mConfirmarSenha = (EditText) view.findViewById(R.id.confirmed_senha);

        containerSenha = view.findViewById(R.id.container_alterar_senha);

        novoPin = (TextView) view.findViewById(R.id.newPin);

        alterarSenhaItem = view.findViewById(R.id.alterarsenha);
        alterarSenhaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containerSenha.setVisibility(View.VISIBLE);
            }
        });

        Button button = (Button) view.findViewById(R.id.btn_loginseguranca);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptNewSenha(view);
            }
        });

        alterarPinItem = view.findViewById(R.id.alterarpin);
        alterarPinItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int pin = application.generatePinSingle();
                    novoPin.setText("PIN: " + pin);
                    Usuario usuario = application.getUserStatus();
                    usuario.setPin(pin);
                    application.updateUsuario(usuario);

                } catch (SQLException e) {
                    e.printStackTrace();
                    Snackbar.make(view, "Erro desconhecido ao alterar o PIN", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        removerConta = view.findViewById(R.id.removerViewConta);
        removerConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Remover conta")
                        .setMessage("Tem certeza que deseja remover esta conta?")
                        .setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    Usuario usuario = application.getUserStatus();
                                    application.deleteUsuario(usuario);
                                    Intent intent = new Intent(getActivity(), SlideActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create().show();
            }
        });

        return view;
    }

    private void attemptNewSenha(View view) {

        mNewSenha.setError(null);
        mConfirmarSenha.setError(null);

        String senha = mNewSenha.getText().toString();
        String confirmedSenha = mConfirmarSenha.getText().toString();

        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(senha)) {
            mNewSenha.setError("Este campo é necessário");
            focusView = mNewSenha;
            cancel = true;
        } else if (!verificarSenhaValida(senha)) {
            mNewSenha.setError("Senha inválida");
            focusView = mNewSenha;
            cancel = true;
        }

        if (TextUtils.isEmpty(confirmedSenha)) {
            mConfirmarSenha.setError("Este campo é necessário");
            focusView = mNewSenha;
            cancel = true;
        } else if (!verificarSenhaValida(confirmedSenha)) {
            mConfirmarSenha.setError("Senha inválida");
            focusView = mNewSenha;
            cancel = true;
        } else if (!confirmedSenha.equalsIgnoreCase(senha)) {
            mConfirmarSenha.setError("As senhas não correspondem");
            focusView = mNewSenha;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            Usuario usuario = application.getUserStatus();
            usuario.setSenha(senha);
            application.updateUsuario(usuario);
            containerSenha.setVisibility(View.GONE);
            Snackbar.make(view, "Senha alterada com sucesso", Snackbar.LENGTH_SHORT).show();

        }

    }

    private Typeface getTypeface() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_regular.ttf");
    }

    private boolean verificarSenhaValida(String senha) {
        return (senha.length() >= 4 && senha.length() <= 16);
    }
}
