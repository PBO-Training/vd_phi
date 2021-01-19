package com.brycen.hrm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Employee Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity {

    /**
     * ID of Employee
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeID;

    /**
     * Code of Employee
     */

    @Column(name = "employee_code", length = 40, nullable = false)
    private String employeeCode;

    /**
     * Last Name of Employee
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * First Name of Employee
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Biography of Employee
     */
    @Column(name = "biography", length = 2000)
    private String biography;

    /**
     * BirthDay of Employee
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * Gender of Employee
     */
    @Column(name = "gender", nullable = false)
    private boolean gender = true;

    /**
     * Email of Employee
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Phone number of Employee
     */
    @Column(name = "phone_number", length = 15, nullable = false)
    private String phone;

    /**
     * Address of Employee
     */
    @Column(name = "address", length = 2000)
    private String address;

    /**
     * Avatar URL of Employee TODO: Change store base64 to store url and resize image
     */
    @Column(name = "avata_url", columnDefinition = "MEDIUMTEXT")
    private String avataUrl;

    /**
     * IdentityCard of Employee
     */
    @Column(name = "identity_card", length = 15, nullable = false)
    private String identityCard;

    /**
     * Year graduate of Employee
     */
    @Column(name = "graduate_year", length = 4)
    private int graduateYear = 2020;

    /**
     * University of Employee
     */
    @Column(name = "university")
    private String university;

    /**
     * Skype of Employee
     */
    @Column(name = "skype")
    private String skype;

    /**
     * Date join Campany
     */
    @Column(name = "date_join_company")
    private Date dateJoinCompany;

    /**
     * Number of children
     */
    @Column(name = "num_of_child")
    private int numOfChild = 0;

    /**
     * Information for contact emergency
     */
    @Column(name = "emergency")
    private String emergency;

    /**
     * Date Of Issue Of ID Card
     */
    @Column(name = "date_of_issue_of_id_card")
    private Date dateOfIssueOfIDCard;

    /**
     * Note for Employee
     */
    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "married")
    private Boolean married;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "status_employee_id")
    private StatusEmployeeEntity statusEmployee;

    @ManyToOne
    @JoinColumn(name = "position_employee_id")
    private PositionEmployeeEntity positionEmployee;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private NationalityEntity nationality;

    /**
     * Degree of Employee
     */
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private DegreeEntity degree;

    /**
     * List employee skill
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeSkillEntity> listEmpSkill;

    /**
     * List Employee Language
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeLanguageEntity> listEmpLanguage;

    /**
     * List Employee Vacation Type
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeVacationTypeEntity> litsEmpVacationType;

    /**
     * List Employee Project
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeProjectEntity> listEmpProject;

    /**
     * List vacation of Employee
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<VacationEntity> listVacation;

    /**
     * List User
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<UserEntity> listUser;

    /**
     * Employee History
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<HistoryWorkEntity> listHistory;

    /**
     * History Members
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<HistoryMemberEntity> listHistoryMembers;

    /**
     * History issue is updated
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<HistoryUpdateIssue> listHistoryUpdateIssue;

    /**
     * List Issues added
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<IssueEntity> listIssue;

    /**
     * Contract History
     */
    // @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    // private List<ContractEntity> listContract;

    @OneToOne(mappedBy = "employee")
    private ContractEntity contract;

    public EmployeeEntity() {

    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvataUrl() {
        return avataUrl;
    }

    public void setAvataUrl(String avataUrl) {
        this.avataUrl = avataUrl;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public Date getDateJoinCompany() {
        return dateJoinCompany;
    }

    public void setDateJoinCompany(Date dateJoinCompany) {
        this.dateJoinCompany = dateJoinCompany;
    }

    public int getNumOfChild() {
        return numOfChild;
    }

    public void setNumOfChild(int numOfChild) {
        this.numOfChild = numOfChild;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public StatusEmployeeEntity getStatusEmployee() {
        return statusEmployee;
    }

    public void setStatusEmployee(StatusEmployeeEntity statusEmployee) {
        this.statusEmployee = statusEmployee;
    }

    public PositionEmployeeEntity getPositionEmployee() {
        return positionEmployee;
    }

    public void setPositionEmployee(PositionEmployeeEntity positionEmployee) {
        this.positionEmployee = positionEmployee;
    }

    public NationalityEntity getNationality() {
        return nationality;
    }

    public void setNationality(NationalityEntity nationality) {
        this.nationality = nationality;
    }

    public List<EmployeeSkillEntity> getListEmpSkill() {
        return listEmpSkill;
    }

    public void setListEmpSkill(List<EmployeeSkillEntity> listEmpSkill) {
        this.listEmpSkill = listEmpSkill;
    }

    public List<EmployeeLanguageEntity> getListEmpLanguage() {
        return listEmpLanguage;
    }

    public void setListEmpLanguage(List<EmployeeLanguageEntity> listEmpLanguage) {
        this.listEmpLanguage = listEmpLanguage;
    }

    public List<EmployeeVacationTypeEntity> getLitsEmpVacationType() {
        return litsEmpVacationType;
    }

    public void setLitsEmpVacationType(List<EmployeeVacationTypeEntity> litsEmpVacationType) {
        this.litsEmpVacationType = litsEmpVacationType;
    }

    public List<EmployeeProjectEntity> getListEmpProject() {
        return listEmpProject;
    }

    public void setListEmpProject(List<EmployeeProjectEntity> listEmpProject) {
        this.listEmpProject = listEmpProject;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public List<VacationEntity> getListVacation() {
        return listVacation;
    }

    public void setListVacation(List<VacationEntity> listVacation) {
        this.listVacation = listVacation;
    }

    public List<UserEntity> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserEntity> listUser) {
        this.listUser = listUser;
    }

    public List<HistoryWorkEntity> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<HistoryWorkEntity> listHistory) {
        this.listHistory = listHistory;
    }

    public Date getDateOfIssueOfIDCard() {
        return dateOfIssueOfIDCard;
    }

    public void setDateOfIssueOfIDCard(Date dateOfIssueOfIDCard) {
        this.dateOfIssueOfIDCard = dateOfIssueOfIDCard;
    }

    public DegreeEntity getDegree() {
        return degree;
    }

    public void setDegree(DegreeEntity degree) {
        this.degree = degree;
    }

    public List<HistoryMemberEntity> getListHistoryMembers() {
        return listHistoryMembers;
    }

    public void setListHistoryMembers(List<HistoryMemberEntity> listHistoryMembers) {
        this.listHistoryMembers = listHistoryMembers;
    }

    public List<HistoryUpdateIssue> getListHistoryUpdateIssue() {
        return listHistoryUpdateIssue;
    }

    public void setListHistoryUpdateIssue(List<HistoryUpdateIssue> listHistoryUpdateIssue) {
        this.listHistoryUpdateIssue = listHistoryUpdateIssue;
    }

    public List<IssueEntity> getListIssue() {
        return listIssue;
    }

    public void setListIssue(List<IssueEntity> listIssue) {
        this.listIssue = listIssue;
    }

}
