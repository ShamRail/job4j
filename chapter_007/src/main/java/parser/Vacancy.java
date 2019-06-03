package parser;

public class Vacancy {

    private String name;

    private String text;

    private String link;

    private VacancyDate vacancyDate;

    public Vacancy(String name, String text, String link, String date) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.vacancyDate = new VacancyDate(date);
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public VacancyDate getVacancyDate() {
        return vacancyDate;
    }

    @Override
    public String toString() {
        return String.format("Date: %s Name: %s Link: %s", vacancyDate, name, link);
    }
}
