package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.icetea.manager.pagodiario.api.dto.TraderDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.TraderDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.transformer.TraderDtoModelTransformer;

@Named
public class TraderServiceImpl 
	extends BasicIdentifiableServiceImpl<Trader, TraderDao, TraderDto, TraderDtoModelTransformer>
		implements TraderService {

	private final BillDao billDao;
	
	@Inject
	public TraderServiceImpl(TraderDao dao,
			TraderDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public TraderDto insert(TraderDto input) {
		
		Trader e = this.getDao().find(input.getDocumentNumber());

		ErrorTypedConditions.checkArgument(e == null, "Vendedor ya existe con dni: " + input.getDocumentNumber(),
				ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(input.getStatus()), "Estado de vendedor es requerido",
				ErrorType.VALIDATION_ERRORS);
		
		e = new Trader();
		e.setAddress(input.getAddress());
		e.setCity(input.getCity());
		e.setDocumentNumber(input.getDocumentNumber());
		e.setDocumentType(input.getDocumentType());
		e.setEmail(input.getEmail());
		e.setName(input.getName());
		e.setNearStreets(input.getNearStreets());
		e.setPhone(input.getPhone());
		e.setSupervisor(input.isSupervisor());
		e.setStatus(Trader.Status.valueOf(input.getStatus()));
		
		if(input.getParentId() != null){
			Trader parent = this.getDao().findById(input.getParentId());
			e.setParent(parent);
			parent.addTrader(e);
			this.getDao().saveOrUpdate(parent);
		}
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public TraderDto update(TraderDto d) {
		ErrorTypedConditions.checkArgument(d.getId() != null, "Id de vendedor requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(d.getStatus()), "Estado de vendedor es requerido",
				ErrorType.VALIDATION_ERRORS);
		
		Trader e = this.getDao().findById(d.getId());
		
		ErrorTypedConditions.checkArgument(e != null, "Vendedor no encontrado con id: " + d.getId(), 
				ErrorType.VALIDATION_ERRORS);
		
		e.setAddress(d.getAddress());
		e.setCity(d.getCity());
		e.setDocumentNumber(d.getDocumentNumber());
		e.setDocumentType(d.getDocumentType());
		e.setEmail(d.getEmail());
		e.setName(d.getName());
		e.setNearStreets(d.getNearStreets());
		e.setPhone(d.getPhone());
		e.setSupervisor(d.isSupervisor());
		e.setStatus(Trader.Status.valueOf(d.getStatus()));
		
		if(d.getParentId() != null){
			ErrorTypedConditions.checkArgument(!d.getId().equals(d.getParentId()), "El vendedor no puede ser supervisor de si mismo", 
					ErrorType.VALIDATION_ERRORS);
			
			Trader parent = this.getDao().findById(d.getParentId());
			e.setParent(parent);
			parent.addTrader(e);
			this.getDao().saveOrUpdate(parent);
		}
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public boolean deleteChild(Long parentId, Long childId){
	
		List<Bill> bills = this.billDao.findByTraderId(childId);
		if(bills != null && !bills.isEmpty()){
			throw new ErrorTypedException("No se puede borrar este vendedor debido a que esta asociado a facturas", 
					ErrorType.VALIDATION_ERRORS);
		}
		
		Trader parent = this.getDao().findById(parentId);
		if(parent != null){
			parent.removeTrader(childId);
		}
		this.getDao().save(parent);
		Trader child = this.getDao().findById(childId);
		if(child != null){
			child.setParent(null);
		}
		this.getDao().saveOrUpdate(child);
		
		return true;
	}
	
	@Override
	public boolean addChild(Long parentId, Long childId){
	
		Trader parent = this.getDao().findById(parentId);
		if(parent != null){
			Trader child = this.getDao().findById(childId);
			if(child == null){
				return false;
			}
			parent.addTrader(child);
			child.setParent(parent);
			this.getDao().save(parent);
			this.getDao().saveOrUpdate(child);
		}
		
		return true;
	}

	@Override
	public boolean remove(Long id) {
		Trader e = this.getDao().findById(id);
		if(e == null){
			throw new RuntimeException(String.format("La entidad con id %s no existe en la bd", id));
		}

		List<Bill> bills = this.billDao.findByTraderId(id);
		
		if(bills != null && !bills.isEmpty()){
			throw new ErrorTypedException("No se puede borrar el vendedor seleccionado debido a que esta asociado a facturas", 
					ErrorType.VALIDATION_ERRORS);
		}
		
		return this.getDao().delete(e);
	}

	@Override
	public List<TraderDto> searchSupervisors(){
		return this.getTransformer().transformAllTo(this.getDao().findSupervisors());
	}

	@Override
	public List<TraderDto> search(String status){
		Trader.Status statusEnum = null;
		if(StringUtils.isNotBlank(status)){
			statusEnum = Trader.Status.valueOf(status);
		}
		
		return this.getTransformer().transformAllTo(this.getDao().find(statusEnum));
	}
	
}
