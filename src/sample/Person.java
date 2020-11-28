package sample;

class Person{
    private  String username;
    private  String position;
    Person(){
        this.username="bataa";
        this.position="user";
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username=username;
    }

    public  String getPosition() {
        return position;
    }

    public  void setPosition(String position) {
        this.position=position;
    }
    public static Person person=new Person();
}

