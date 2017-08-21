package com.QA.util;

import com.QA.po.Answer;
import com.QA.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SqlDataMaker {
    @Autowired
    public static AnswerService answerService;

    public static void addAnswer(){
        Date date = new Date();
        Answer answer = new Answer("<p>水一水</p>",
                "long","images/temp.jpg",5,date);
        for(int i=0; i<40; i++){
            answerService.saveAnswer(answer);
        }
        System.out.println("success");
    }

    public static void main(String[] args){
        addAnswer();
    }
}
