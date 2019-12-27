package com.tlf.oss.resourceorder.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Conjunto de componentes de rede
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class NetworkComponent {

    /**
     * Lista de componentes de rede
     */
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private STATUS status;

    public enum STATUS {
        ATIVADO, DESATIVADO, PENDENTE
    }

}