package br.com.ufpb.nowpersonal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ruan on 07/06/2016.
 */
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
public class DBCoreSQL extends SQLiteOpenHelper{

    private static final String DB_NAME = "NowPersonalDB";
    private static final int DB_VERSION = 6;

    private DBCode dbCode;

    public DBCoreSQL(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dbCode = new DBCode();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String s : dbCode.createTables()){
            db.execSQL("CREATE TABLE " + s);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE usuario");
        onCreate(db);
    }
}
