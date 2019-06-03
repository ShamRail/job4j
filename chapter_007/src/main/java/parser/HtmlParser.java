package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class HtmlParser {

    private static final Logger LOG = LogManager.getLogger(HtmlParser.class.getName());

    private static final String SN = System.lineSeparator();

    /**
     * Beginning of a year.
     * */

    private static final VacancyDate BOUND = new VacancyDate(
                    String.format("31 дек %s, 23:59",
                    (Calendar.getInstance().get(Calendar.YEAR) - 1) % 100)
    );

    /**
     * Retrieve job's data(for java) from https://www.sql.ru/forum/job-offers/.
     * Parse name, link, text and date of vacancy, create object and put it into list.
     * At first, check that analysing vacancy follows after last added vacancy and after beginning of year.
     * At second, if condition is met then parse data, else parsing is terminated.
     * @param previous represents last added vacancy.
     * @return list of vacancies.
     */

    public List<Vacancy> extract(long previous) {
        LOG.debug("Parsing has running ...");
        List<Vacancy> result = new LinkedList<>();
        boolean stop = false;
        int page;
        try {
            for (page = 1; page <= 15 && !stop; page++) {
                LOG.debug(String.format("Analyse page number: %s", page));
                Document doc = Jsoup.connect(String.format("https://www.sql.ru/forum/job-offers/%s", page)).get();
                Elements table = doc.getElementsByClass("forumTable").get(0).getElementsByTag("tr");
                for (int i = 1; i < table.size() && !stop; i++) {
                    String date = table.get(i).getElementsByTag("td").get(5).text();
                    VacancyDate current = new VacancyDate(date);
                    stop = current.after(previous);
                    if (!stop && current.after(BOUND.getTimeDate())) {
                        Element el = table.get(i).getElementsByTag("a").get(0);
                        String name = el.text();
                        String nameLowercase = name.toLowerCase();
                        if (nameLowercase.contains("java") && !nameLowercase.contains("javascript")) {
                            String link = el.attr("href");
                            Document msgDoc = Jsoup.connect(link).get();
                            String text = msgDoc
                                    .getElementsByClass("msgBody")
                                    .get(1)
                                    .html()
                                    .replaceAll("</*br>", SN)
                                    .replaceAll("</*u>", "")
                                    .replaceAll("</*b>", "")
                                    .replaceAll("</*li>", "");
                            int index = text.indexOf("<table");
                            text = text.substring(0, index == -1 ? text.length() : index).trim();
                            result.add(new Vacancy(name, text, link, date));
                        }
                    }
                }
            }
            LOG.debug(String.format("Parsing has finished. Page analysed: %s", page - 1));
        } catch (IOException e) {
            LOG.error(String.format("Parsing has terminated with. %s", e.getMessage()));
        }
        return result;
    }

}
