package model;

import kotlin.Pair;

import java.text.MessageFormat;

@Model("student")
public class Student {
    @Field("id")
    private String id;

    @Field("name")
    private String name;

    @Field("person_id")
    private String personId;

    @Field("class")
//    @ForeignKey("id")
    private Integer clazz;

    public Student() {
    }

    public Student(String id, String name, String personId, Clazz clazz) {
        this.id = id;
        this.name = name;
        this.personId = personId;
        this.clazz = clazz.getId();
    }

    public Clazz getClazz() {
        return new SQL().query(Clazz.class, new Pair<String, Integer>("id", clazz));
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Student'{'id=''{0}'', name=''{1}'', personId=''{2}'', clazz={3}'}'", id, name, personId, clazz);
    }
}
