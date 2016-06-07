package br.com.ufpb.nowpersonal;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.ufpb.nowpersonal.database.DBController;
import br.com.ufpb.nowpersonal.database.DBCoreORM;
import br.com.ufpb.nowpersonal.database.UsuarioDao;
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
public class NowPersonalApplication extends MultiDexApplication {

    private Usuario usuario;
    private DBCoreORM dbCoreORM;
    private UsuarioDao usuarioDao;

    private DBController dbController;
    private List<Usuario> usuarios;


    @Override
    public void onCreate() {
        super.onCreate();
        dbController = new DBController(getBaseContext());
        usuarios = getAllUsers();
        //dbCoreORM = new DBCoreORM(getBaseContext());
        //instanciarUsuarioDao(dbCoreORM.getConnectionSource());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        dbCoreORM.close();
    }

    private void instanciarUsuarioDao(ConnectionSource connectionSource) {
        try {
            usuarioDao = new UsuarioDao(connectionSource);
            Log.i("user", "iniciou o banco");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdateUsuario(Usuario usuario) throws SQLException {
        //usuarioDao.createOrUpdate(usuario);
        dbController.addUser(usuario);

    }

    public void updateUsuario(Usuario usuario){
        dbController.updateUsuario(usuario);
        if (usuario.isStatus()) {
            this.usuario = usuario;
        }else{
            this.usuario = null;
        }
    }

    public void deleteUsuario(Usuario usuario) throws SQLException {
        //usuarioDao.delete(usuario);
        usuarios.remove(usuario);
        this.usuario = null;
    }

    public List<Usuario> searchAllPersonal(){
        List<Usuario> usuarios = new ArrayList<>();
        /*for (Usuario u : usuarioDao.queryForAll()) {
            if (u.isPersonal()) {
                usuarios.add(u);
            }
        }
        return usuarios; */
        for(Usuario usuario : this.usuarios){
            Log.i("user", "lista de personais:" + usuario.isPersonal());
            if(usuario.isPersonal()){
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario getUserStatus() {
        return this.usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int generatePinSingle() throws SQLException {
        List<Integer> pins = getAllPin();
        Random random = new Random();
        final int maxPin = 999;
        int pin = random.nextInt(maxPin);
        while (pins.contains(pin)) {
            pin = random.nextInt(maxPin);
        }
        return pin;
    }

    private List<Integer> getAllPin() throws SQLException {
        List<Integer> pins = new ArrayList<>();
        for (Usuario u : usuarios) {
            pins.add(u.getPin());
        }
        return pins;
    }

    private List<Usuario> getAllUsers() {
        //return usuarioDao.queryForAll();
        return dbController.getAllUsers();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void refreshUsers(){
        usuarios = getAllUsers();
    }
}
