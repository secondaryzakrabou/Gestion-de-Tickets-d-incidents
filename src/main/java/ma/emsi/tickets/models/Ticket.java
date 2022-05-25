package ma.emsi.tickets.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String description;
	private String urgence;
	private String env;
	private String logiciel;
	private String etat;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Ticket(long id, String description, String urgence, String env, String logiciel, String etat) {
		super();
		this.id = id;
		this.description = description;
		this.urgence = urgence;
		this.env = env;
		this.logiciel = logiciel;
		this.etat = etat;
	}
	public Ticket() {
		super();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrgence() {
		return urgence;
	}
	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getLogiciel() {
		return logiciel;
	}
	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
}
