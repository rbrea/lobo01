package com.icetea.manager.pagodiario.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "bill_history")
public class BillHistory extends Identifiable {

	private static final long serialVersionUID = 1L;

}
