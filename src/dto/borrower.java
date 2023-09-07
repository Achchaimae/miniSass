package dto;

public class borrower {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;


    private static borrower instance;

    private borrower(){}

    // Public method to get the Singleton instance
    public static borrower getInstance(){
        if(instance==null)
        {
            instance = new borrower();
        }
        return instance;
    }
    private borrower(int id,String name, String email, String phone, int age){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.age=age;

    }
    private borrower(String name){
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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





    public static void setInstance(borrower instance) {
        borrower.instance = instance;
    }
}
