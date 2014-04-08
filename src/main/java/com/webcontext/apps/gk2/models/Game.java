package com.webcontext.apps.gk2.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity modelizing a Video game.
 * 
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 * @since 2014/04/08
 * @version 1.0
 */
@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min = 2, max = 50)
	private String title;

	@Size(min = 10, max = 300)
	private String description;

	@Size(min = 0, max = 255)
	private String cover;

	@NotNull
	private float userRate;

	private float kommunityRate;

	@Temporal(TemporalType.DATE)
	private Date createdAt;

	private String createdBy;

	/**
	 * Default constructor.
	 */
	public Game() {
		super();
	}

	/**
	 * Parameterized constructor to initialize a new Game.
	 * 
	 * @param id
	 * @param title
	 * @param description
	 * @param cover
	 * @param userRate
	 * @param kommunityRate
	 * @param createdAt
	 * @param createdBy
	 */
	public Game(String title, String description, String cover,
			float userRate, float kommunityRate, Date createdAt,
			String createdBy) {
		super();
		this.title = title;
		this.description = description;
		this.cover = cover;
		this.userRate = userRate;
		this.kommunityRate = kommunityRate;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover
	 *            the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * @return the userRate
	 */
	public float getUserRate() {
		return userRate;
	}

	/**
	 * @param userRate
	 *            the userRate to set
	 */
	public void setUserRate(float userRate) {
		this.userRate = userRate;
	}

	/**
	 * @return the kommunityRate
	 */
	public float getKommunityRate() {
		return kommunityRate;
	}

	/**
	 * @param kommunityRate
	 *            the kommunityRate to set
	 */
	public void setKommunityRate(float kommunityRate) {
		this.kommunityRate = kommunityRate;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append("Game[").append("title:").append(title)
				.append(",description:").append(description)
				.append(",userRate:").append(userRate)
				.append(",kommunityRate:").append(kommunityRate)
				.append(",createdAt:").append(createdAt).append(",createdBy:")
				.append(createdBy).toString();
	}
}
