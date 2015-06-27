package com.icetea.manager.pagodiario.transformer;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.model.Model;

public interface DtoModelTransformer<D extends BasicDto, E extends Model> {

    D transform(E e);

    D transform(E e, int depth);

    List<D> transformAllTo(List<E> entities);

    List<D> transformAllTo(List<E> entities, int depth);

}
