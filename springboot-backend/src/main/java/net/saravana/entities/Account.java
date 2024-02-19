package net.saravana.entities;

import javax.persistence.*;

@Entity
@Table(name = "Account_Users", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "userName", unique = true)
	private String userName;

	@Column(name = "password")
	private String password;
	@Column(name = "phone")
	private String phone;

	public Account() {

	}

	public Account(String userName, String password, String phone) {
		super();
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
