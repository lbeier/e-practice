package models.analisadorLexico;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentificadorDeToken {

    private static final Map<String, String> SIMBOLOS_RESERVADOS = new HashMap<String, String>() {{
        put("var", "PALAVRA_RESERVADA");
        put("String", "TIPO_DE_VARIAVEL");
        put("varres", "PALAVRA_RESERVADA");
        put("Inteiro", "TIPO_DE_VARIAVEL");
        put("=", "IGUAL");
        put(":", "DECLARACAO");
        put("+", "ADICAO");
        put("-", "SUBTRACAO");
        put("*", "MULTIPLICACAO");
        put("/", "DIVISAO");
        put("(", "PARENTESES_ABERTO");
        put(")", "PARENTESES_FECHADO");
        put("<>", "CONCATENACAO");
    }};

    public String identifica(String token) {

        if(SIMBOLOS_RESERVADOS.containsKey(token)) {
            return SIMBOLOS_RESERVADOS.get(token);
        }

        // TODO: O que IDV significa?
        if (tokenComecaComLetra(token)) {
            return "IDV";
        }

        if (tokenComecaETerminaComAspas(token)) {
            return "CONSTANTE_TIPO_STRING";
        }

        if (verificaSeTodasOsCaracteresSaoNumeros(token)) {
            return "NUMERO";
        }

        return "ERRO";
    }

    private static boolean tokenComecaComLetra(String token) {
        Pattern pattern = Pattern.compile("^[a-zA-Z_]");
        Matcher matcher = pattern.matcher(token);

        return matcher.find();
    }

    private static boolean tokenComecaETerminaComAspas(String token) {
        Pattern pattern = Pattern.compile("\".*\"");
        Matcher matcher = pattern.matcher(token);

        return matcher.find();
    }

    public boolean verificaSeTodasOsCaracteresSaoNumeros(String token){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(token);

        return matcher.find();
    }
}