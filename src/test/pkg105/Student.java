
package test.pkg105;

import java.io.Serializable;

 class Student implements Serializable{
     
    private String Name;
    private String SirName;
    private int age;
    private String ID;
    private float Average;

    public Student() {
        this("", "", 0 , 0 , "");
    }

    public Student(String Name,String Sirname, int age, float average , String ID) {
        this.ID = ID;
        this.Name = Name;
        this.age = age;
        this.Average = average;
        this.SirName = Sirname;
                
    }

    public String get_name() {
        return this.Name;
    }
    
    public String get_Sirname() {
        return this.SirName;
    }
    

    public int get_age() {
        return this.age;
    }
    
    public float get_Average() {
        return this.Average;
    }
    
    public String get_ID() {
        return this.ID;
    }

    public void set_Name(String s) {
        this.Name = s;
    }

    public void set_age(int age) {
        this.age = age;
    }

    public void set_ID(String ID) {
        this.ID = ID;
    }
    
    public void set_Sirname(String s) {
        this.SirName = s;
    }
    
    public void set_Average(float f) {
        this.Average = f;
    }
    

}
