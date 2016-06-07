package br.com.ufpb.nowpersonal.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Ruan on 02/06/2016.
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
@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable {

    @DatabaseField(generatedId = true, dataType = DataType.LONG)
    private long id;
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isStatus;
    @DatabaseField(dataType = DataType.STRING)
    private String nome;
    @DatabaseField(dataType = DataType.STRING)
    private String sobrenome;
    @DatabaseField(dataType = DataType.STRING)
    private String dataNascimento;
    @DatabaseField(dataType = DataType.STRING)
    private String telefone;
    @DatabaseField(dataType = DataType.STRING)
    private String email;
    @DatabaseField(dataType = DataType.STRING)
    private String senha;
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isPersonal;
    @DatabaseField(dataType = DataType.STRING)
    private String localTrabalho;
    @DatabaseField(dataType = DataType.STRING)
    private String horaComeco;
    @DatabaseField(dataType = DataType.STRING)
    private String horaFim;
    @DatabaseField(dataType = DataType.STRING)
    private String preco;
    @DatabaseField(dataType = DataType.INTEGER)
    private int cref;
    @DatabaseField(dataType = DataType.INTEGER)
    private int pin;
    @DatabaseField (dataType = DataType.STRING)
    private String endereco;
    @DatabaseField (dataType = DataType.STRING)
    private String bairro;

    public Usuario() {
    }

    public Usuario(long id, boolean isStatus, String nome, String sobrenome, String dataNascimento, String telefone, String email, String senha, boolean isPersonal, String localTrabalho, String horaComeco, String horaFim, String preco, int cref, int pin, String endereco, String bairro) {
        this.id = id;
        this.isStatus = isStatus;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.isPersonal = isPersonal;
        this.localTrabalho = localTrabalho;
        this.horaComeco = horaComeco;
        this.horaFim = horaFim;
        this.preco = preco;
        this.cref = cref;
        this.pin = pin;
        this.endereco = endereco;
        this.bairro = bairro;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public String getHoraComeco() {
        return horaComeco;
    }

    public void setHoraComeco(String horaComeco) {
        this.horaComeco = horaComeco;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        isPersonal = personal;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getCref() {
        return cref;
    }

    public void setCref(int cref) {
        this.cref = cref;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}