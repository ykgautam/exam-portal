package com.exam.controller;

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

import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // add Quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    // update Quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuiz(quiz));
    }

    // get all quizzes
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzez());
    }

    // get single quiz
    @GetMapping("/{quizId}")
    public Quiz getQuize(@PathVariable("quizId") Long quizId) {
        return quizService.getQuiz(quizId);
    }

    // delete quiz
    @DeleteMapping("/{quizId}")
    public void deleteQuize(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
    }
}
