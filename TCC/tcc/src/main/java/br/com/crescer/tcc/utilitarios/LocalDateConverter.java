/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.utilitarios;

/**
 *
 * @author luan.avila
 */

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    
  @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Optional
                .ofNullable(Date.valueOf(localDate))
                .orElse(null);
    }
    
  @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return Optional
                .ofNullable(date.toLocalDate())
                .orElse(null);
    }   
}