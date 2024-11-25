package main.java.triviaGame;

public class Question {
    private String question;
    private String firstAns;
    private String secondAns;
    private String thirdAns;
    private String fourAns;

    public String getQuestion(){
        return question;
    }
    public String getFirstAns(){
        return firstAns;
    }
    public String getSecondAns(){
        return secondAns;
    }
    public String getThirdAns(){
        return thirdAns;
    }
    public String getFourAns(){
        return fourAns;
    }

    public String setQuestion(String str){
        return question = str;
    }
    public String setFirstAns(String str){
        return firstAns = str;
    }
    public String setSecondAns(String str){
        return secondAns = str;
    }
    public String setThirdAns(String str){
        return thirdAns = str;
    }
    public String setFourAns(String str){
        return fourAns = str;
    }
    Question() {}
    Question(String question, String firstAns, String secondAns, String thirdAns, String fourAns) {
        this.question = question;
        this.firstAns = firstAns;
        this.secondAns = secondAns;
        this.thirdAns = thirdAns;
        this.fourAns = fourAns;
    }
}
