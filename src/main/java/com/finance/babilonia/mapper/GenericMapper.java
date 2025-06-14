package com.finance.babilonia.mapper;

import com.sun.tools.rngom.digested.DXmlTokenPattern;

import java.util.List;

public interface GenericMapper<E,D,I> {

    D entityToDTO(E e);
    E dtoToEntity(D d);
    List<D> dtoToEntitys( List<E> e);
}
