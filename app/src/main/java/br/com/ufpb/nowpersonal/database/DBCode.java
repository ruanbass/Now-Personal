package br.com.ufpb.nowpersonal.database;

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
public class DBCode {

    public List<String> createTables(){
        List<String> tables = new ArrayList<>();

        tables.add("usuario (_id integer primary key autoincrement," +
                " status varchar(5), nome varchar, sobrenome varchar, " +
                "dataNascimento varchar, telefone varchar, email varchar, " +
                "senha varchar(16), personal varchar(5), local varchar, " +
                "horaInicio varchar, horaFim varchar, preco varchar(10), " +
                "cref integer(12), pin integer(5), endereco varchar, " +
                "bairro varchar)");

        return tables;
    }

}
