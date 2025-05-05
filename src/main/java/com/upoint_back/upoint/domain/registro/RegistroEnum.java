package com.upoint_back.upoint.domain.registro;

public enum RegistroEnum {
    ENTRADA ("entrada"),
    SAIDA_INTERVALO ("saida_intervalo"),
    RETORNO_INTERVALO ("retorno_intervalo"),
    SAIDA("saida");

    private String registro;

    RegistroEnum(String registro) {
        this.registro = registro;
    }

    public String getRegistro() {
        return registro;
    }

}
