package com.pro.ynov.com.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> firstName;
	public static volatile SingularAttribute<Employee, String> lastName;
	public static volatile SingularAttribute<Employee, Instant> hireDate;
	public static volatile SingularAttribute<Employee, String> phoneNumber;
	public static volatile SingularAttribute<Employee, Employee> manager;
	public static volatile SetAttribute<Employee, Job> jobs;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, Long> salary;
	public static volatile SingularAttribute<Employee, Department> department;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, Long> commissionPct;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String HIRE_DATE = "hireDate";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String MANAGER = "manager";
	public static final String JOBS = "jobs";
	public static final String ID = "id";
	public static final String SALARY = "salary";
	public static final String DEPARTMENT = "department";
	public static final String EMAIL = "email";
	public static final String COMMISSION_PCT = "commissionPct";

}

