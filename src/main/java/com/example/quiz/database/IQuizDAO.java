package com.example.quiz.database;

import com.example.quiz.modules.*;

import java.sql.SQLException;
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

    void addPlayer(Player player) throws SQLException;
    void addQuiz(Quiz quiz) throws SQLException;
    void addAsk(Ask ask) throws SQLException;
    void addAnswer(Answer answer) throws SQLException;
    void addTopic(Topic topic) throws SQLException;
    void addQuestion(Question question) throws SQLException;
    void addBelong(Belong belong) throws SQLException;
    void addSubTopic(Subtopic subtopic) throws SQLException;
    void addPlay(Play play) throws SQLException;

    void deletePlayer(String userName) throws SQLException;


    void updatePlayer(Player player) throws SQLException;
}
