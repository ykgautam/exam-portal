package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    // add Question
    @PostMapping("/")
    public ResponseEntity<Question> addQuiz(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    // update Question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    // get all Questions of any quiz
    @GetMapping("/quiz/{quizId}")
    public ArrayList<Question> getQuestions(@PathVariable("quizId") Long quizId) {
//        Quiz quiz=new Quiz();
//        quiz.setQid(quizId);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = quizService.getQuiz(quizId);
        Set<Question> question = quiz.getQuestion();
        ArrayList<Question> list = new ArrayList<Question>(question);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
            list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion() + 1));
        }
        Collections.shuffle(list);
        return list;
    }

    // get single Question
    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestion(@PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(questionService.getQuestion(questionId));
    }

    // delete Question
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionService.deleteQuestion(questionId);
    }

}
