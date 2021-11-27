import java.io.Serializable;

public class Patient implements Serializable {
    private String Name;
    static int addid=0;
    private int id;
    private String phoneNumber;

    public Patient(String Name, String phoneNumber){
        this.Name=Name;
        this.phoneNumber=phoneNumber;
        addid+=1;
        id=addid;
    }

    public String phoneNum(){
        return phoneNumber;
    }
    public String patientName(){
        return Name;
    }
    public int patientId(){
        return id;
    }


}
