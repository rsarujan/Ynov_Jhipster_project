package com.pro.ynov.com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ {

	public static volatile SingularAttribute<Location, Country> country;
	public static volatile SingularAttribute<Location, String> streetAddress;
	public static volatile SingularAttribute<Location, String> city;
	public static volatile SingularAttribute<Location, String> postalCode;
	public static volatile SingularAttribute<Location, String> stateProvince;
	public static volatile SingularAttribute<Location, Long> id;

	public static final String COUNTRY = "country";
	public static final String STREET_ADDRESS = "streetAddress";
	public static final String CITY = "city";
	public static final String POSTAL_CODE = "postalCode";
	public static final String STATE_PROVINCE = "stateProvince";
	public static final String ID = "id";

}

