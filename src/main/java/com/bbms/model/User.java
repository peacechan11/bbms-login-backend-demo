package com.bbms.model;

import java.io.Serializable;


import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="userName")
	private String userName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="createAt")
	private Date createAt;
	@Column(name="updateAt")
	private Date updateAt;
	@Column(name="isEnabled")
	private boolean isEnabled;
}