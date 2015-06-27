package com.icetea.manager.pagodiario.transformer;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.model.Model;

public abstract class AbstractDtoModelTransformer<D extends BasicDto, E extends Model>
    implements DtoModelTransformer<D, E> {

    @Override
    public D transform(E e) {
        return this.transform(e, Integer.MAX_VALUE);
    }

    @Override
    public D transform(E e, int depth) {
        if (e == null) {
            return null;
        }
        return this.doTransform(e, depth);
    }

    protected abstract D doTransform(E e, int depth);

    @Override
    public final List<D> transformAllTo(List<E> entities) {
        return this.transformAllTo(entities, Integer.MAX_VALUE);
    }

    @Override
    public final List<D> transformAllTo(List<E> entities, int depth) {
        if (entities == null) {
            return null;
        }
        List<D> result = Lists.newArrayList();
        
        for(E e : entities){
        	result.add(this.doTransform(e, depth));
        }

        return result;
    }

}
