package com.pro.ynov.com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livre.class)
public abstract class Livre_ {

	public static volatile SingularAttribute<Livre, Emplacement> emplacement;
	public static volatile SetAttribute<Livre, Theme> themes;
	public static volatile SingularAttribute<Livre, String> code;
	public static volatile SetAttribute<Livre, Exemplaire> exemplaires;
	public static volatile SetAttribute<Livre, Auteur> auteurs;
	public static volatile SingularAttribute<Livre, String> titre;
	public static volatile SingularAttribute<Livre, String> isbn;
	public static volatile SingularAttribute<Livre, String> description;
	public static volatile SingularAttribute<Livre, Long> id;

	public static final String EMPLACEMENT = "emplacement";
	public static final String THEMES = "themes";
	public static final String CODE = "code";
	public static final String EXEMPLAIRES = "exemplaires";
	public static final String AUTEURS = "auteurs";
	public static final String TITRE = "titre";
	public static final String ISBN = "isbn";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

