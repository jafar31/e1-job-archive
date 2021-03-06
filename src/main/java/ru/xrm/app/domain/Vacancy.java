package ru.xrm.app.domain;

import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Type;

@Entity
@NamedQueries({
	@NamedQuery(name="Vacancy.findByCategoryId", query="from Vacancy v where v.section.id=:id"),
	@NamedQuery(name="Vacancy.countByCategoryId", query="select count(v) from Vacancy v where v.section.id=:id")
})
public class Vacancy {

	@Id
	@Column(name="vacancy_id")
	private Long id;

	@Column(name="salary")
	private Long salary;

	@Column(name = "job_title")
	private String jobTitle;

	@ManyToOne
	@JoinColumn(name = "dutytype_id")
	private DutyType dutyType;

	@ManyToOne
	@JoinColumn(name="education_id")
	private Education education;

	@Column(name="experience")
	private Long experience;

	@ManyToOne
	@JoinColumn(name="schedule")
	private Schedule schedule;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	@ManyToOne
	@JoinColumn(name="employer_id")
	private Employer employer;

	@Column(name = "vacancy_date")
	@Type(type = "date")
	private Date date;

	@Column(name = "contact_information")
	private String contactInformation;

	@Column(name = "presented_by")
	private String presentedBy;

	@Column(name="body")
	@Lob 
	private String body;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public DutyType getDutyType() {
		return dutyType;
	}

	public void setDutyType(DutyType dutyType) {
		this.dutyType = dutyType;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Long getExperience() {
		return experience;
	}

	public void setExperience(Long experience) {
		this.experience = experience;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
		section.getVacancies().add(this);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getPresentedBy() {
		return presentedBy;
	}

	public void setPresentedBy(String presentedBy) {
		this.presentedBy = presentedBy;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Vacancy other = (Vacancy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void setProperty(String property, Object value)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		if ("section".equals(property)){
			this.setSection((Section)value);
		}else{
			@SuppressWarnings("rawtypes")
			Class aClass = getClass();
			Field field = aClass.getDeclaredField(property);
			field.set(this, value);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append("{\n");
		try {
			for (Field f : getClass().getDeclaredFields()) {
				sb.append("\t");
				sb.append(f.getName());
				sb.append(" : ");

				if ("ru.xrm.app.domain.Section".equals(f.get(this).getClass().getName())){
					sb.append("ru.xrm.app.domain.Section");
				}
				else{
					sb.append(f.get(this));
				}
				sb.append("\n");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		sb.append("}\n");
		return sb.toString();
	}

}
