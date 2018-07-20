package model.entity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Document {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String ruc;
	@Persistent
	private String dni;
	@Persistent
	private String businessReason;
	@Persistent
	private String direction;
	@Persistent
	private Double amount;
	
	
	public Document(String ruc, String dni, String businessReason,
			String direction, Double amount) {
		super();
		this.ruc = ruc;
		this.dni = dni;
		this.businessReason = businessReason;
		this.direction = direction;
		this.amount = amount;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getBusinessReason() {
		return businessReason;
	}


	public void setBusinessReason(String businessReason) {
		this.businessReason = businessReason;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direcction) {
		this.direction = direcction;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
