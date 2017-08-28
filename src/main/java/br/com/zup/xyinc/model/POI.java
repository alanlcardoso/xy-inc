package br.com.zup.xyinc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "poi")
public class POI {

	@Id
	@GenericGenerator(name = "generator_poi", strategy = "increment")
	@GeneratedValue(generator = "generator_poi")
	private Long id;

	public POI() {
	}

	public POI(String nome, int coordX, int coordY) {
		this.nome = nome;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	@NotNull
	@Size(min = 0)
	private String nome;

	@NotNull
	@Min(value = 0)
	private Integer coordX;

	@NotNull
	@Min(value = 0)
	private Integer coordY;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordX;
		result = prime * result + coordY;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POI other = (POI) obj;
		if (coordX != other.coordX)
			return false;
		if (coordY != other.coordY)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}