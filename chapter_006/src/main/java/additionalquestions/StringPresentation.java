package additionalquestions;

public class StringPresentation {

    private StringBuilder stringBuilder = new StringBuilder();

    public String appendNumber(int number) {
        return (stringBuilder.append(number)).toString();
    }
}
