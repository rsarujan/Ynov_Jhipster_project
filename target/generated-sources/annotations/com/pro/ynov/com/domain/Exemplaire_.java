package com.pro.ynov.com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Exemplaire.class)
public abstract class Exemplaire_ {

	public static volatile SingularAttribute<Exemplaire, Boolean> disponibilite;
	public static volatile SingularAttribute<Exemplaire, Long> id;
	public static volatile SingularAttribute<Exemplaire, Livre> livre;

	public static final String DISPONIBILITE = "disponibilite";
	public static final String ID = "id";
	public static final String LIVRE = "livre";

}

