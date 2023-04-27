package com.example.quiz.database;

import com.example.quiz.modules.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IQuizDAO {

    List<Player> getPlayers();
    List<Quiz> getQuizzes();
    List<Ask> getAsks();
    List<Answer> getAnswers();
    List<Topic> getTopics();
    List<Question> getQuestions();
    List<RankingPoint> getRankingPoints();
    List<Subtopic> getSubtopics();
    List<Play> getPlays();

    void addPlayer(Player player) throws SQLException;
    void addQuiz(Quiz quiz) throws SQLException;
    void addAsk(Ask ask) throws SQLException;
    void addAnswer(Answer answer) throws SQLException;
    void addTopic(Topic topic) throws SQLException;
    void addQuestion(Question question) throws SQLException;
    void addRankingPoint(RankingPoint rankingPoint) throws SQLException;
    void addSubTopic(Subtopic subtopic) throws SQLException;
    void addPlay(Play play) throws SQLException;

    void deletePlayer(String userName) throws SQLException;
    void deleteQuestion(int questionId) throws SQLException;
    void deleteTopic(String topicName) throws SQLException;
    void deleteSubtopic(String subtopicName) throws SQLException;
    void deleteQuiz(int quizId) throws SQLException;
    void deleteAnswer(int answerId) throws SQLException;
    void deleteAsk(int quiestionId, int quizID) throws SQLException;
    void deletePlay(String playerId, int quizID) throws SQLException;
    void deleteRankingPoint(int pId) throws SQLException;

    void updatePlayer(Player player) throws SQLException;
    void updateTopic(Topic topic) throws SQLException;
    void updateSubtopic(Subtopic subtopic) throws SQLException;
    void updateQuiz(Quiz quiz) throws SQLException;
    void updateQuestion(Question question) throws SQLException;
    void updateAnswer(Answer answer) throws SQLException;
    void updateRankingPoints(RankingPoint rankingPoint) throws SQLException;

    List<String> questionsOfPlayedQuiz(int quizID) throws SQLException; //osszetett
    List<Integer> playedQuizId(Player player) throws SQLException;
    List<QuestionCount> listMostFrequentQuestionsPlayedUser(String userName) throws SQLException; //osszetett
    List<RankingByUser> ranking() throws SQLException; //osszetett
    List<RankingByTopic> rankingByTopic(String userName) throws SQLException; //osszetett
    List<SubtopicDescByTopic> subtopicDescriptionByTopic() throws SQLException; //osszetett
    List<String> playersWithBigRankingPoints() throws SQLException; //osszetett
}
