package org.jsp.hospitaladminapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Hospital {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true)
	private String name,founder,gst;
	@Column(nullable = false)
	private int estb;
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admin admin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public int getEstb() {
		return estb;
	}
	public void setEstb(int estb) {
		this.estb = estb;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", founder=" + founder + ", gst=" + gst + ", estb=" + estb
				+ ", admin=" + admin + "]";
	}
	
	
	

}
