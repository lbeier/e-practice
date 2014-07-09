package models.analisadorSintatico;

import models.analisadorLexico.IdentificadorDeToken;

import java.util.ArrayList;

/**
 * Created by alunos3 on 07/07/14.
 */
public class ValidadorDeDeclaracaoDeVariavel {

    IdentificadorDeToken identificadorDeToken;
    ArrayList<String> tokens;
    String erros;

    public ValidadorDeDeclaracaoDeVariavel(ArrayList<String> tokens) {
        this.identificadorDeToken = new IdentificadorDeToken();
        this.tokens = tokens;
        this.erros = null;
    }

    public String validaPrimeiroToken() {
        String retorno = "";
        if(identificadorDeToken.identifica(tokens.get(0)).equals("PALAVRA_RESERVADA")){
            retorno = "var é o Primeiro token";
        }else
            erros += retorno = "a primeira palavra deveria ser \"var\"";
        return retorno;
    }

    public String validaSegundoToken() {
        String retorno = "";
        if(identificadorDeToken.identifica(tokens.get(1)).equals("IDV")){
            retorno = "IDV é o Segundo token";
        }else
            erros += retorno = "a segunda palavra deveria ser um identificador de variável válido";
        return retorno;
    }

    public String validaTerceiroToken() {
        String retorno = "";
        if(identificadorDeToken.identifica(tokens.get(2)).equals("DECLARACAO")){
            retorno = ": é o Terceiro token";
        }else
            erros += retorno = "a terceira palavra deveria ser :";
        return retorno;

    }

    public String validaQuartoToken() {
        String retorno = "";
        if(identificadorDeToken.identifica(tokens.get(3)).equals("TIPO_DE_VARIAVEL")){
            retorno = "Tipo é o Quarto token";
        }else
            erros += retorno = "a quarta palavra deveria ser um tipo válido de variável (string ou inteiro)";
        return retorno;
    }

    public String valida() {
        this.validaPrimeiroToken();
        this.validaSegundoToken();
        this.validaTerceiroToken();
        this.validaQuartoToken();
        return erros;
    }
}
