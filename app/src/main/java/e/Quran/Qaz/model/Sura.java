
package e.Quran.Qaz.model;

public class Sura {

    public int id;
    public String kazakh_names;
    public String arabic_names;
    public String lengthSura;
    public String kuranAddress;


    public Sura(Integer id, String kazakh_names, String arabic_names, String lengthSura, String kuranAddress) {
        this.id = id;
        this.kazakh_names = kazakh_names;
        this.arabic_names = arabic_names;
        this.lengthSura = lengthSura;
        this.kuranAddress = kuranAddress;
    }

    public int getId() {
        return id;
    }

    public String getKazakh_names() {
        return kazakh_names;
    }

    public String getArabic_names() {
        return arabic_names;
    }

    public String getLengthSura() {
        return lengthSura;
    }

    public String getKuranAddress() {
        return kuranAddress;
    }
}
