package com.pro.ynov.com.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Emprunt.class)
public abstract class Emprunt_ {

	public static volatile SingularAttribute<Emprunt, LocalDate> dateEmprunt;
	public static volatile SingularAttribute<Emprunt, Long> id;
	public static volatile SingularAttribute<Emprunt, Exemplaire> exemplaire;
	public static volatile SingularAttribute<Emprunt, User> user;

	public static final String DATE_EMPRUNT = "dateEmprunt";
	public static final String ID = "id";
	public static final String EXEMPLAIRE = "exemplaire";
	public static final String USER = "user";

}

