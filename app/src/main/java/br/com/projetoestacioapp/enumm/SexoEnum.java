package br.com.projetoestacioapp.enumm;

/**
 * Created by feito on 21/09/2017.
 */

public enum SexoEnum {

    NAO_INFORMAR("Não informado"),
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String name;

    SexoEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
