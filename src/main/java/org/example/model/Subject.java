package org.example.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Subject {
    private String title;
    private int mark;

    public Subject() {
        super();
    }

    public Subject(String title, int mark) {
        this.title = title;
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    @XmlAttribute
    public void setTitle(String title) {
        this.title = title;
    }

    public int getMark() {
        return mark;
    }
    @XmlAttribute
    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return getMark() == subject.getMark() && Objects.equals(getTitle(), subject.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getMark());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subject{");
        sb.append("title='").append(title).append('\'');
        sb.append(", mark=").append(mark);
        sb.append('}');
        return sb.toString();
    }
}
