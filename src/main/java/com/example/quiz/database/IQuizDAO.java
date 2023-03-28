package com.example.quiz.database;

import com.example.quiz.modules.*;

import java.util.List;

public interface IQuizDAO {

    List<Player> getPlayers();
    List<Quiz> getQuizzes();
    List<Ask> getAsks();
    List<Answer> getAnswers();
    List<Topic> getTopics();
    List<Question> getQuestions();
    List<Belong> getBelongs();
    List<Subtopic> getSubtopics();
    List<Play> getPlays();
}
