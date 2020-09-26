package models;

public class Move {
    String noNameYet;
    String operator;

    public Move() {
    }

    public Move(String noNameYet, String operator) {
        this.noNameYet = noNameYet;
        this.operator = operator;
    }

    public String getNoNameYet() {
        return noNameYet;
    }

    public void setNoNameYet(String noNameYet) {
        this.noNameYet = noNameYet;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Move{" +
                "noNameYet='" + noNameYet + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
