package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column (name = "first_name")
	private String firstName;
	@Column (name = "last_name")
	private String lastName;
	@Column (name = "address")
	private String address;
	@Column (name = "account_number")
	private long accountNumber;
	@Column (name = "grade")
	private String grade;
	@Column (name = "manager")
	private long managerId;
}
