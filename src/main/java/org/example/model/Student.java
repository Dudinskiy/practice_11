package org.example.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Set;

@XmlRootElement
public class Student {
    private String firstName;
    private String lastName;
    private String groupNumber;
    private Set<Subject> subjects;
    private double average;

    public Student() {
        super();
    }

    public Student(String firstname, String lastname
            , String group, Set<Subject> subjects) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.groupNumber = group;
        this.subjects = subjects;
    }

    public Student(String firstname, String lastname
            , String group) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.groupNumber = group;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlAttribute
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlAttribute
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    @XmlAttribute
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    @XmlElement(name = "subject")
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @XmlElement
    public void setAverage(double average) {
        if (average == getAverage()) {
            this.average = average;
        } else {
            this.average = getAverage();
        }
    }

    public double getAverage() {
        int sumMarks = 0;
        int markCount = 0;
        double result = 0;
        for (Subject subject : subjects) {
            if (subject.getMark() != 0) {
                sumMarks = sumMarks + subject.getMark();
                markCount++;
            }
        }
        if (markCount != 0) {
            result = (double) sumMarks / markCount;
            BigDecimal res = new BigDecimal(result)
                    .setScale(1, RoundingMode.HALF_UP);
            result = res.doubleValue();
        }
        return average = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getAverage() == student.getAverage() && Objects.equals(getFirstName()
                , student.getFirstName()) && Objects.equals(getLastName()
                , student.getLastName()) && Objects.equals(getGroupNumber()
                , student.getGroupNumber()) && Objects.equals(getSubjects()
                , student.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName()
                , getLastName()
                , getGroupNumber()
                , getSubjects()
                , getAverage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("firstname='").append(firstName).append('\'');
        sb.append(", lastname='").append(lastName).append('\'');
        sb.append(", group=").append(groupNumber);
        sb.append(", subjects=").append(subjects);
        sb.append(", average=").append(average);
        sb.append('}');
        return sb.toString();
    }
}
