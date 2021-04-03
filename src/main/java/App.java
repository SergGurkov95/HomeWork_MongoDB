import com.mongodb.client.model.Filters;
import dao.UserDao;
import model.Job;
import model.User;


import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        List<User> titleAge = userDao.findAllByFilter(Filters
                .and(Filters.eq("title", "Senior Java Developer") , Filters.lte("age", 26)));


        List<User> juniors = userDao.findAllByFilter(Filters.regex("title", "Junior"));


        List<User> workedInAmazon = userDao.findAllByFilter(Filters
                .elemMatch("jobs", Filters.in("organization", "Amazon")));


        List<User> haveExp  = userDao.findAllByFilter(Filters.exists("jobs.3"));



        System.out.println(titleAge);
        System.out.println(juniors);
        System.out.println(workedInAmazon);
        System.out.println(haveExp);
    }

    private static User createUser(int age, String fullName, String title, List<Job> job) {
        User user = new User();
        user.setAge(age);
        user.setFullName(fullName);
        user.setTitle(title);
        user.setJobs(job);
        return user;
    }

    private static Job createJob(String organization, String title){
        Job job = new Job();
        job.setOrganization(organization);
        job.setTitle(title);
        return job;
    }
}
