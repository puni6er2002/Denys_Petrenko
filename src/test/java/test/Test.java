package test;

import app.Dropbox;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class Test {
    private static final Dropbox dp = new Dropbox();
    private boolean failure = false;

    @Given("User's dropbox")
    public void initialization() {

    }

    @When("User uploads a file with {string}, {string} and {string}")
    public void uploadFile(String path, String filename, String content) {
        try {
            dp.UploadFile("/" ,"test", "My name is Petrenko Denys");
            System.out.println("D");
        }
        catch (com.mashape.unirest.http.exceptions.UnirestException error) {
            System.out.println(error.getMessage());
        }
    }

    @Then("the file has to be uploaded successfully")
    public void checkStatus() {
        if (dp.getLastGivenStatus() >= 300)
            throw new IllegalStateException("File is not uploaded!");
    }


    @When("User passes {string}")
    public void getFileMetadata(String string) {
        try {
            dp.GetFileMetadata(string);
        }
        catch (com.mashape.unirest.http.exceptions.UnirestException error) {
            System.out.println(error.getMessage());
        }
    }

    @Then("the content of file has to be returned")
    public void checkStatus2() {
        if (dp.getLastGivenStatus() >= 300)
            throw new IllegalStateException("File is not found!");
    }


    @When("User deletes file with {string} and {string}")
    public void delete(String path, String filename) {
        try {
            dp.Delete(path,filename);
        }
        catch (com.mashape.unirest.http.exceptions.UnirestException error) {
            System.out.println(error.getMessage());
        }
    }

    @Then("the file has to be deleted")
    public void checkStatus3() {
        if (dp.getLastGivenStatus() >= 300)
            throw new IllegalStateException("File is not deleted!");
    }
}
