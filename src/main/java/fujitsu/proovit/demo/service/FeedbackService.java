package fujitsu.proovit.demo.service;



import fujitsu.proovit.demo.entities.Feedback;
import fujitsu.proovit.demo.repositories.FeedbackRepo;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo repository; // repository which holds all the feedbacks.

    /**
     * Method to get all the feedbacks from db.
     * @return list of all the feedbacks.
     */
    public List<Feedback> getAllFeedbacks() {
        return repository.findAll().stream().filter(this::isCorrectGet).collect(Collectors.toList());
    }

    /**
     * Method to enter new Feedback object into repository.
     * @param feedback new feedback which is to be inserted into the repository.
     */
    public void insert(Feedback feedback) {
        if (isCorrectPost(feedback))
            repository.save(feedback);
        else
            throw new InputMismatchException("Retrieved feedback does not match the pattern for the expected type.");;
    }

    /**
     * Method to check if the given feedback is formatted correctly.
     * @param feedback The new feedback which has been entered.
     * @return if all the requirements are met then returns true else false.
     */
    private boolean isCorrectPost(Feedback feedback) {
        if (feedback.getName() != null && feedback.getEmail().contains("@")
                && isValidEmail(feedback.getEmail()) && feedback.getText() != null
                && feedback.getCategory() != null) {
            String categories = convertCategoryToString(feedback.getCategory().split(","));
            feedback.setCategory(categories); // Setting correct categories for feedback
            return true;
        } else
            return false;
    }

    /**
     * Method to check if the feedbacks from repository are formatted correctly.
     * @param feedback feedback from repository.
     * @return if all the requirements are met then returns true else false.
     */
    private boolean isCorrectGet(Feedback feedback) {
        String[] c = {"Patients portal", "Doctors portal", "Registration", "Virtual visit", "OpenKM", "Microsoft SharePoint"};
        List<String> categoryList = Arrays.asList(c);
        if (feedback.getName() != null && feedback.getEmail().contains("@")
                && isValidEmail(feedback.getEmail()) && feedback.getText() != null
                && feedback.getCategory() != null) {
            String[] categorys = feedback.getCategory().split(",");
            for (String category : categorys) {
                if (!categoryList.contains(category.trim()))
                    throw new InputMismatchException("Retrieved feedback does not match the pattern for the expected type.");;
            }
            return true;
        } else
            throw new InputMismatchException("Retrieved feedback does not match the pattern for the expected type.");
    }

    /**
     * Method to convert given numbers from frontend to correct categories.
     * @param arr Array of given values in order to find correct categories for them.
     * @return String of categories.
     */
    private String convertCategoryToString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            switch (s) {
                case "3":
                    sb.append(", " + "Patients portal");
                    break;
                case "4":
                    sb.append(", " + "Doctors portal");
                    break;
                case "6":
                    sb.append(", " + "Registration");
                    break;
                case "7":
                    sb.append(", " + "Virtual visit");
                    break;
                case "9":
                    sb.append(", " + "OpenKM");
                    break;
                case "10":
                    sb.append(", " + "Microsoft SharePoint");
                    break;
                default:
                    throw new IllegalArgumentException("There is no such option. ");
            }
        }
        return sb.deleteCharAt(0).toString();
    }

    /**
     * Method to check if email is formatted correctly
     * @param email Given email
     * @return true if email is formatted correctly else false.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}

