package kodlamaio.HRMS.entities.concretes;

import javax.persistence.Entity;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="system_users")
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser  extends User{
	
	
	private String firstName;
	private String lastName;
}
