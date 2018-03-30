package com.commas.sudharshaun.perception;

public class UserData {
    String questionID;
    String getAnswer1;
    String getAnswer2;
    String getAnswer3;

    public UserData() {

    }

    public UserData(String questionID, String getAnswer1, String getAnswer2, String getAnswer3) {
        this.questionID = questionID;
        this.getAnswer1 = getAnswer1;
        this.getAnswer2 = getAnswer2;
        this.getAnswer3 = getAnswer3;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getGetAnswer1() {
        return getAnswer1;
    }

    public String getGetAnswer2() {
        return getAnswer2;
    }

    public String getGetAnswer3() {
        return getAnswer3;
    }
}
