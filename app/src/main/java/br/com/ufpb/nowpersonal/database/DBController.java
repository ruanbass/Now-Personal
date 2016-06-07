package br.com.ufpb.nowpersonal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
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
import br.com.ufpb.nowpersonal.model.Usuario;

/**
 * Created by Ruan on 07/06/2016.
 */
public class DBController {

    private DBCoreSQL dbCore;
    private SQLiteDatabase db;

    public DBController(Context context) {
        dbCore = new DBCoreSQL(context);
    }

    public void addUser(Usuario usuario) {
        // Permission to write to the database
        db = dbCore.getWritableDatabase();
        // Prepare to send the values to the DB
        ContentValues values = new ContentValues();
        values.put("nome", "" + usuario.getNome());
        values.put("sobrenome", "" + usuario.getSobrenome());
        values.put("status", usuario.isStatus());
        values.put("dataNascimento", usuario.getDataNascimento());
        values.put("telefone", usuario.getTelefone());
        values.put("email", "" + usuario.getEmail());
        values.put("senha", "" + usuario.getSenha());
        values.put("personal", "" + usuario.isPersonal());
        values.put("local", "" + usuario.getLocalTrabalho());
        values.put("horaInicio", "" + usuario.getHoraComeco());
        values.put("horaFim", "" + usuario.getHoraFim());
        values.put("preco", "" + usuario.getPreco());
        values.put("cref", "" + usuario.getCref());
        values.put("pin", "" + usuario.getPin());
        values.put("endereco", "" + usuario.getEndereco());
        values.put("bairro", "" + usuario.getBairro());
        // Insert in DB
        db.insert("usuario", null, values);
        // Closes the connection to the DB
        Log.i("user", "cadastrou");
        db.close();
    }

    public void updateUsuario(Usuario usuario) {
        db = dbCore.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", "" + usuario.getNome());
        values.put("sobrenome", "" + usuario.getSobrenome());
        values.put("status", usuario.isStatus());
        values.put("dataNascimento", usuario.getDataNascimento());
        values.put("telefone", usuario.getTelefone());
        values.put("email", "" + usuario.getEmail());
        values.put("senha", "" + usuario.getSenha());
        values.put("personal", "" + usuario.isPersonal());
        values.put("local", "" + usuario.getLocalTrabalho());
        values.put("horaInicio", "" + usuario.getHoraComeco());
        values.put("horaFim", "" + usuario.getHoraFim());
        values.put("preco", "" + usuario.getPreco());
        values.put("cref", "" + usuario.getCref());
        values.put("pin", "" + usuario.getPin());
        values.put("endereco", "" + usuario.getEndereco());
        values.put("bairro", "" + usuario.getBairro());
        db.update("usuario", values, "_id = ?", new String[]{"" + usuario.getId()});
        db.close();
    }

    public List<Usuario> getAllUsers() {
        db = dbCore.getReadableDatabase();
        List<Usuario> users = new ArrayList<>();
        Usuario user = null;
        String[] coluns = new String[]{"_id", "status", "nome", "sobrenome", "dataNascimento", "telefone", "email", "senha",
        "personal", "local", "horaInicio", "horaFim", "preco", "cref", "pin", "endereco", "bairro"};
        Cursor cursor = db.query("usuario", coluns, null, null, null, null, "nome ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                user = new Usuario(cursor.getLong(0), cursor.getString(1).equalsIgnoreCase("true"), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8).equalsIgnoreCase("true"), cursor.getString(9), cursor.getString(10),
                        cursor.getString(11), cursor.getString(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15), cursor.getString(16));
                users.add(user);
            } while (cursor.moveToNext());
        }
        Log.i("user", "usuarios: " + users.size());
        cursor.close();
        return users;
    }

}
