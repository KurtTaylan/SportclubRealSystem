package com.taylan.persistence.DAO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


/**
 *
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 * 
 *          Personal Trainers Data Annotation
 */
@NamedQueries({
		@NamedQuery(name = "loadPersonalTrainers", query = "from Personaltrainers")
})

@Entity
@Table(name = "personaltrainers", catalog = "sportclubsystem")
public class Personaltrainers implements java.io.Serializable {

	private Integer idpersonalTrainers;
	private String name;
	private int experience;
	private String email;
	private String contact;
        
        public Personaltrainers() {
        
        }

	public Personaltrainers(Integer id,String name, int experience, String email,
			String contact) {
                this.idpersonalTrainers = id;        
		this.name = name;
		this.experience = experience;
		this.email = email;
		this.contact = contact;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpersonalTrainers", unique = true, nullable = false)
	public Integer getIdpersonalTrainers() {
		return this.idpersonalTrainers;
	}

	public void setIdpersonalTrainers(Integer idpersonalTrainers) {
		this.idpersonalTrainers = idpersonalTrainers; 
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "experience", nullable = false)
	public int getExperience() {
		return this.experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contact", nullable = false, length = 45)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
