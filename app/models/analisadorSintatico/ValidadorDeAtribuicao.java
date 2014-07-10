package models.analisadorSintatico;

import models.analisadorLexico.IdentificadorDeToken;
import models.analisadorLexico.Lexer;

import java.util.ArrayList;

public class ValidadorDeAtribuicao {
    private Lexer lexer;
    private IdentificadorDeToken identificadorDeTokens;
    private ArrayList<String> tokens;

    public ValidadorDeAtribuicao(Lexer lexer, IdentificadorDeToken identificadorDeTokens) {
        this.lexer = lexer;
        this.identificadorDeTokens = identificadorDeTokens;
    }

    public boolean validaPrimeiroToken(String primeiroToken) {

        String token = identificadorDeTokens.identifica(primeiroToken);
        if(token == "IDV")
            return true;
        else
            return false;
    }

    public boolean validaSegundoToken(String segundoToken){
        String token = identificadorDeTokens.identifica(segundoToken);
        if(token == "IGUAL")
            return true;
        else
            return false;
    }

    public boolean validaTerceiroToken(String terceiroToken) {

        String token = identificadorDeTokens.identifica(terceiroToken);
        if(token == "NUMERO")
            return true;
        else
            return false;
    }

    public boolean validaExpressao(String frase) {
        tokens = stringParaArray(frase);
        boolean retorno = true;
        for(int i = 2; i < tokens.size(); i++){
            if(i % 2 == 0 && !identificadorDeTokens.identifica(tokens.get(i)).equals("NUMERO")) retorno = false;
            if(i % 2 == 1 && !identificadorDeTokens.identifica(tokens.get(i)).equals("SUBTRACAO")) {
                if (i % 2 == 1 && !identificadorDeTokens.identifica(tokens.get(i)).equals("ADICAO")){
                    if (i % 2 == 1 && !identificadorDeTokens.identifica(tokens.get(i)).equals("MULTIPLICACAO")){
                        if (i % 2 == 1 && !identificadorDeTokens.identifica(tokens.get(i)).equals("DIVISAO")){
                            retorno = false;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    private ArrayList<String> stringParaArray(String frase) {
        tokens = lexer.tokenizar(frase);
        return tokens;
    }

    public boolean validaIdv(String frase) {
       boolean retorno = true;
       stringParaArray(frase);

       return retorno;

    }
}
