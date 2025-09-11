public class Guest {
    private String name;
    private String surname;
    private int age;

    private int num;

    public Guest(String name, String surname, int age, int num){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWordForAge(int num){
        if(num % 10 == 1)
            return "года";
        else return "лет";
    }
}
