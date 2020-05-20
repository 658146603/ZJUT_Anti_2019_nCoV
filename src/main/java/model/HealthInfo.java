package model;

@Model("health_info")
public class HealthInfo {
    public HealthInfo(Person person, String tel, boolean danger14, boolean abroad14, boolean touchIllness14, boolean ill, int status) {
        this.person = person;
        this.tel = tel;
        this.danger14 = danger14;
        this.abroad14 = abroad14;
        this.touchIllness14 = touchIllness14;
        this.ill = ill;
        this.status = new Status(status);
    }

    @Field("uid")
    private Person person;

    @Field("tel")
    private String tel;

    @Field("if_danger_14")
    private boolean danger14;

    @Field("if_abroad_14")
    private boolean abroad14;

    @Field("if_touch_illness")
    private boolean touchIllness14;

    @Field("if_ill")
    private boolean ill;

    @Field("status")
    private Status status;

    @Field("color")
    private String color;

    public HealthInfo() {
    }

    public CodeColor getCodeColor() {
        var codeColor = CodeColor.GREEN;
        var abnormalCount = getStatus().getAbnormalCount();

        if (danger14 || abroad14 || abnormalCount == 1) {
            codeColor = CodeColor.YELLOW;
        }

        if (touchIllness14 || ill || abnormalCount >= 2) {
            codeColor = CodeColor.RED;
        }

        return codeColor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isDanger14() {
        return danger14;
    }

    public void setDanger14(boolean danger14) {
        this.danger14 = danger14;
    }

    public boolean isAbroad14() {
        return abroad14;
    }

    public void setAbroad14(boolean abroad14) {
        this.abroad14 = abroad14;
    }

    public boolean isTouchIllness14() {
        return touchIllness14;
    }

    public void setTouchIllness14(boolean touchIllness14) {
        this.touchIllness14 = touchIllness14;
    }

    public boolean isIll() {
        return ill;
    }

    public void setIll(boolean ill) {
        this.ill = ill;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "HealthInfo{" +
                "person=" + person +
                ", tel='" + tel + '\'' +
                ", danger14=" + danger14 +
                ", abroad14=" + abroad14 +
                ", touchIllness14=" + touchIllness14 +
                ", ill=" + ill +
                ", status=" + status +
                ", color='" + color + '\'' +
                '}';
    }
}

enum CodeColor {
    RED(255, 0, 0), YELLOW(255, 255, 0), GREEN(0, 255, 0);

    int r, g, b;

    CodeColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}

interface HealthCodeTester {
    CodeColor getCodeColor();

    HealthInfo getHealthInfo();

    PunchRecord[] getPunchRecords();
}