package br.com.elo.domain.dto;


import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MappingToModel {


    public static Converter<String, LocalDate> toStringDate = new AbstractConverter<>() {
        @Override
        protected LocalDate convert(String source) {
            if (Objects.nonNull(source)) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(source, format);
            }
            return null;
        }
    };

}