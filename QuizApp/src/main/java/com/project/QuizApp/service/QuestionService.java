package com.project.QuizApp.service;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>("Question Added Successfully",HttpStatus.CREATED);
    }

    public ResponseEntity<String> addQuestions(List<Question> questions) {
        questionRepository.saveAll(questions);
        return new ResponseEntity<>("Questions Added Successfully",HttpStatus.CREATED);

    }
    public ResponseEntity<Question> getQuestionById(int id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> updateQuestion(int id, Question updatedQuestion) {
        Optional<Question> questionOpt = questionRepository.findById(id);
        if (questionOpt.isPresent()) {
            Question question = questionOpt.get();
            question.setQuestionTitle(updatedQuestion.getQuestionTitle());
            question.setOption1(updatedQuestion.getOption1());
            question.setOption2(updatedQuestion.getOption2());
            question.setOption3(updatedQuestion.getOption3());
            question.setOption4(updatedQuestion.getOption4());
            question.setRightAnswer(updatedQuestion.getRightAnswer());
            question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            question.setCategory(updatedQuestion.getCategory());
            questionRepository.save(question);
            return new ResponseEntity<>("Question Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
            return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Question>> getRandomQuestionsByCategory(String category, int numQ) {
        try {
            return new ResponseEntity<>(questionRepository.findRandomQuestionsByCategory(category, numQ), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Long> countQuestionsByCategory(String category) {
        try {
            long count = questionRepository.findByCategory(category).size();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByDifficulty(String level) {
        try {
            List<Question> questions = questionRepository.findAll()
                    .stream()
                    .filter(q -> q.getDifficultyLevel().equalsIgnoreCase(level))
                    .toList();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Page<Question>> viewQuestions(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Question> questions = questionRepository.findAll(pageable);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Page.empty(), HttpStatus.BAD_REQUEST);
        }
    }

}
