package br.com.elo.domain;

import lombok.Getter;

@Getter
public enum SchemaVersion {
    SCHEMA_VERSION_1("1");

    private final String version;


    SchemaVersion(String version) {
        this.version = version;
    }
}
