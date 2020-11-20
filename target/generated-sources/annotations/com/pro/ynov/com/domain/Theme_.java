package com.pro.ynov.com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Theme.class)
public abstract class Theme_ {

	public static volatile SingularAttribute<Theme, String> theme;
	public static volatile SingularAttribute<Theme, Long> id;
	public static volatile SetAttribute<Theme, Livre> livres;

	public static final String THEME = "theme";
	public static final String ID = "id";
	public static final String LIVRES = "livres";

}

