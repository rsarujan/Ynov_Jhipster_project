package com.pro.ynov.com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Job.class)
public abstract class Job_ {

	public static volatile SingularAttribute<Job, Long> maxSalary;
	public static volatile SingularAttribute<Job, String> jobTitle;
	public static volatile SingularAttribute<Job, Long> id;
	public static volatile SingularAttribute<Job, Long> minSalary;
	public static volatile SingularAttribute<Job, Employee> employee;
	public static volatile SetAttribute<Job, Task> tasks;

	public static final String MAX_SALARY = "maxSalary";
	public static final String JOB_TITLE = "jobTitle";
	public static final String ID = "id";
	public static final String MIN_SALARY = "minSalary";
	public static final String EMPLOYEE = "employee";
	public static final String TASKS = "tasks";

}

