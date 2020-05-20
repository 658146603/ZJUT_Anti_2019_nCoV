package model;

import java.text.MessageFormat;
import java.util.Objects;

public class Person implements HealthCodeTester {
    @Field("id")
    private String uid;
    @Field("name")
    private String name;
    @Field("person_id")
    private String personId;

    public Person() {
    }

    public Person(String uid, String name, String personId) {
        this.uid = uid;
        this.name = name;
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CodeColor getCodeColor() {
        // TODO: 2020/5/17

        var healthInfo = getHealthInfo();

        var cardColor = healthInfo.getCodeColor();
        var punchRecords = getPunchRecords();

        for (var record : punchRecords) {

        }

        return null;
    }

    @Override
    public HealthInfo getHealthInfo() {
        // TODO: 2020/5/17

        return null;
    }

    @Override
    public PunchRecord[] getPunchRecords() {
        // TODO: 2020/5/17
        return new PunchRecord[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(uid, person.uid) &&
                Objects.equals(name, person.name) &&
                Objects.equals(personId, person.personId);
    }


    public Boolean login(String id, String name, String password) {
        if (this instanceof Student) {

        } else if (this instanceof Teacher) {

        } else {

        }

        return false;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Person'{'uid=''{0}'', name=''{1}'', personId=''{2}'''}'", uid, name, personId);
    }
}