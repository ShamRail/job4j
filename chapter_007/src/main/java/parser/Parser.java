package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Parser implements Job {

    private static final Logger LOG = LogManager.getLogger(Parser.class.getName());

    private static Properties config;

    private VacancyDB vacancyDB;

    private HtmlParser htmlParser;

    public Parser() {
        LOG.debug("Parser is triggered ...");
        vacancyDB = new VacancyDB(config);
        htmlParser = new HtmlParser();
    }

    /**
     * First launch.
     * */

    private void setup() {
        LOG.debug("Extracting data ...");
        List<Vacancy> data = htmlParser.extract(System.currentTimeMillis());
        LOG.debug(String.format("Extracting is finished. %s vacancies was found.", data.size()));
        vacancyDB.addData(data);
        LOG.debug("All done.");
    }

    /**
     * Next running.
     */

    private void parseJob() {
        long prev = vacancyDB.lastJobTime();
        LOG.debug("Extracting data ...");
        LOG.debug(String.format("Time of last updated vacancy: %s.", new Date(prev)));
        List<Vacancy> data = htmlParser.extract(prev);
        LOG.debug(String.format("Extracting is finished. %s vacancies was found.", data.size()));
        if (data.size() > 0) {
            vacancyDB.addData(data);
        }
        LOG.info("All done.");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        parseJob();
    }


    public static void main(String[] args) {
        Parser.initConfiguration();
        Parser parser = new Parser();
        parser.setup();
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(Parser.class).build();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(CronScheduleBuilder.cronSchedule(config.getProperty("cron")))
                    .build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.fatal("Invalid schedule option!");
        }
    }

    private static void initConfiguration() {
        LOG.debug("Loading DB properties ...");
        try (InputStream in = Parser.class.getClassLoader().getResourceAsStream("job.properties")) {
            config = new Properties();
            config.load(in);
            LOG.debug("Properties is loaded.");
        } catch (Exception e) {
            LOG.debug("Loading has fallen.");
            e.printStackTrace();
        }
    }
}
