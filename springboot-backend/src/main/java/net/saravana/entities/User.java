package net.saravana.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Account_Users", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "userName", unique = true)
	private String userName;

	@Column(name = "password")
	private String password;
	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;
	// Many users can have one role
	@ManyToOne
	@JoinColumn(name = "role_id")  // This specifies the foreign key column in the User table
	private Role role;

	public User() {

	}

	public User(String userName, String password, String phone, String email, Role role) {
		super();
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.role = role;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
