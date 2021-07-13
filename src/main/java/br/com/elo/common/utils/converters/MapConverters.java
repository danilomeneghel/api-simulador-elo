package br.com.elo.common.utils.converters;

import br.com.elo.domain.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class MapConverters {

    public static Converter<Integer, Status> toStatus = new AbstractConverter<Integer, Status>() {
        @Override
        protected Status convert(Integer integer) {
            return Status.get(integer);
        }

        protected Enum<?> convert(String source) {
            return source == null ? null : Status.get(Integer.getInteger(source));
        }
    };

    public static Converter<Integer, Tipo> toTipo = new AbstractConverter<Integer, Tipo>() {
        @Override
        protected Tipo convert(Integer integer) {
            return Tipo.get(integer);
        }

        protected Enum<?> convert(String source) {
            return source == null ? null : Tipo.get(Integer.getInteger(source));
        }
    };

    public static Converter<Integer, TipoProtocolo> toTipoProtocolo = new AbstractConverter<Integer, TipoProtocolo>() {
        @Override
        protected TipoProtocolo convert(Integer integer) {
            return TipoProtocolo.get(integer);
        }

        protected Enum<?> convert(String source) {
            return source == null ? null : TipoProtocolo.get(Integer.getInteger(source));
        }
    };

    public static Converter<Integer, TipoMensagem> toTipoMensagem = new AbstractConverter<Integer, TipoMensagem>() {
        @Override
        protected TipoMensagem convert(Integer integer) {
            return TipoMensagem.get(integer);
        }

        protected Enum<?> convert(String source) {
            return source == null ? null : TipoMensagem.get(Integer.getInteger(source));
        }
    };

    public static Converter<Integer, TipoPlataforma> toTipoPlataforma = new AbstractConverter<Integer, TipoPlataforma>() {
        @Override
        protected TipoPlataforma convert(Integer integer) {
            return TipoPlataforma.get(integer);
        }

        protected Enum<?> convert(String source) {
            return source == null ? null : TipoPlataforma.get(Integer.getInteger(source));
        }
    };

    public static Converter<Integer, TipoCartao> toTipoCartao = new AbstractConverter<Integer, TipoCartao>() {
        @Override
        protected TipoCartao convert(Integer integer) {
            return TipoCartao.get(integer);
        }

        protected Enum<?> convert(String source) {
            return source == null ? null : TipoCartao.get(Integer.getInteger(source));
        }
    };

}
