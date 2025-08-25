package com.project.QuizApp.controller;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions){
        return questionService.addQuestions(questions);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
        return questionService.getQuestionById(id);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id, @RequestBody Question updatedQuestion) {
        return questionService.updateQuestion(id, updatedQuestion);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/random/{category}/{numQ}")
    public ResponseEntity<List<Question>> getRandomQuestionsByCategory(
            @PathVariable String category,
            @PathVariable int numQ) {
        return questionService.getRandomQuestionsByCategory(category, numQ);
    }

    @GetMapping("/countByCategory/{category}")
    public ResponseEntity<Long> countQuestionsByCategory(@PathVariable String category) {
        return questionService.countQuestionsByCategory(category);
    }

    @GetMapping("/difficulty/{level}")
    public ResponseEntity<List<Question>> getQuestionsByDifficulty(@PathVariable String level) {
        return questionService.getQuestionsByDifficulty(level);
    }

    // View all questions with pagination
    @GetMapping("/viewQuestions")
    public ResponseEntity<Page<Question>> viewQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return questionService.viewQuestions(page, size);
    }



}
