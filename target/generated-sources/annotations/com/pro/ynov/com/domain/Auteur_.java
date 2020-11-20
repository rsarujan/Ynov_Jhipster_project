package com.pro.ynov.com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auteur.class)
public abstract class Auteur_ {

	public static volatile SingularAttribute<Auteur, Long> id;
	public static volatile SetAttribute<Auteur, Livre> livres;
	public static volatile SingularAttribute<Auteur, String> auteur;

	public static final String ID = "id";
	public static final String LIVRES = "livres";
	public static final String AUTEUR = "auteur";

}

