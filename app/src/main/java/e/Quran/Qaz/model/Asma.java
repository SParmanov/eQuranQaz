package e.Quran.Qaz.model;

public class Asma {

    private int number;
    private String nameKazakh;
    private String nameArab;


    public Asma(Integer number,String nameKazakh, String nameArab) {
        this.number = number;
        this.nameKazakh = nameKazakh;
        this.nameArab = nameArab;
    }

    public String getNameKazakh() {
        return nameKazakh;
    }
    public String getNameArab() {
        return nameArab;
    }
    public Integer getNumber() {
        return number;
    }
}


