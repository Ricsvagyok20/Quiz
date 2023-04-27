package com.example.quiz.database;

import com.example.quiz.modules.*;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QuizDAO implements IQuizDAO {

    private Statement statement;
    private Connection conn;
    private ResultSet rs;
    private String sql;
    private OracleDataSource ods;

    Connection DAO(){
        try {
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            return ods.getConnection("system", "oracle");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getPlayers(){
        List<Player> players = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM JATEKOS";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Player tmp = new Player(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4));
                players.add(tmp);
                //System.out.println(players);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public List<Quiz> getQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM QUIZ";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Quiz tmp = new Quiz(rs.getInt(1), rs.getString(2));
                quizzes.add(tmp);
                //System.out.println(quizzes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public List<Ask> getAsks() {
        List<Ask> ask = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM FELTESZI";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Ask tmp = new Ask(rs.getInt(1), rs.getInt(2));
                ask.add(tmp);
                //System.out.println(ask);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ask;
    }

    @Override
    public List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM VALASZ";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Answer tmp = new Answer(rs.getInt(1), rs.getInt(2) , rs.getString(3), rs.getString(4));
                answers.add(tmp);
                //System.out.println(answers);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM TEMA";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Topic tmp = new Topic(rs.getString(1));
                topics.add(tmp);
                //System.out.println(topics);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM KERDES";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Question tmp = new Question(rs.getInt(1), rs.getString(2), rs.getString(3));
                questions.add(tmp);
                //System.out.println(questions);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public List<RankingPoint> getRankingPoints() {
        List<RankingPoint> rankingPoints = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM PONTSZAM";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                RankingPoint tmp = new RankingPoint(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                rankingPoints.add(tmp);
                //System.out.println(rankingPoints);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rankingPoints;
    }

    @Override
    public List<Subtopic> getSubtopics() {
        List<Subtopic> subtopics = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM ALTEMA";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Subtopic tmp = new Subtopic(rs.getString(1), rs.getString(2), rs.getString(3));
                subtopics.add(tmp);
                //System.out.println(subtopics);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subtopics;
    }

    @Override
    public List<Play> getPlays() {
        List<Play> plays = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM JATSZIK";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Play tmp = new Play(rs.getString(1), rs.getInt(2));
                plays.add(tmp);
                //System.out.println(plays);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plays;
    }

    public void addPlayer(Player player) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(player.getTopicNamePlayer() == null){
                sql = "INSERT INTO JATEKOS " +
                        "VALUES ('"+player.getUserName()+"', '"+player.getPassword()+"', '"+player.getEmail()+"', "+null+")";
            }
            else{
            sql = "INSERT INTO JATEKOS " +
                    "VALUES ('"+player.getUserName()+"', '"+player.getPassword()+"', '"+player.getEmail()+"', '"+player.getTopicNamePlayer()+"')";
            }
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addQuiz(Quiz quiz) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO QUIZ(quiztema) " +
                    "VALUES ('"+quiz.getTopicName()+"')";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addAsk(Ask ask) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO FELTESZI " +
                    "VALUES ("+ask.getQuestionIdAsk()+", "+ask.getQuizIdAsk()+")";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addAnswer(Answer answer) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO VALASZ(kerdesid, valasztartalma, helyese) " +
                    "VALUES ("+answer.getQuestionId()+", '"+answer.getAnswerContent()+"', '"+answer.getCorrectAnswer()+"')";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addTopic(Topic topic) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO TEMA " +
                    "VALUES ('"+topic.getTopicName()+"')";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addQuestion(Question question) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO KERDES(kerdestartalma, altema) " +
                    "VALUES ('"+question.getQuestionContent()+"', '"+question.getSubtopicNameQuestion()+"')";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addRankingPoint(RankingPoint rankingPoint) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO PONTSZAM(FNEV, TNEV, RANGSORPONTSZAM) " +
                    "VALUES ('"+rankingPoint.getUserNameRankingPoint()+"', '"+rankingPoint.getTopicNameRankingPoint()+"', "+rankingPoint.getRankingPoint()+")";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addSubTopic(Subtopic subtopic) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO ALTEMA " +
                    "VALUES ('"+subtopic.getSubtopicName()+"', '"+subtopic.getDescription()+"', '"+subtopic.getTopicNameSubtopic()+"')";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addPlay(Play play) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO JATSZIK " +
                    "VALUES ('"+play.getUserNamePlay()+"', "+play.getQuizIdPlay()+")";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deletePlayer(String userName) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM JATEKOS where FELHASZNALONEV='"+ userName+"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteQuestion(int questionId) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM KERDES where ID="+ questionId;
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteTopic(String topicName) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM TEMA where NEV='"+ topicName+"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteSubtopic(String subtopicName) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM ALTEMA where NEV='"+ subtopicName+"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteQuiz(int quizId) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM QUIZ where QUIZID="+ quizId;
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteAnswer(int answerId) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM VALASZ where VALASZID="+ answerId;
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteAsk(int quiestionId, int quizID) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM FELTESZI where KERDES="+ quiestionId+" AND QUIZ="+ quizID;
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deletePlay(String userName, int quizID) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM JATSZIK where FELHASZNALO='"+ userName+"' AND QID="+ quizID;
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteRankingPoint(int pId) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM PONTSZAM where PID = " + pId;
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updatePlayer(Player player) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE JATEKOS set JELSZO='"
                    +player.getPassword()+"', EMAIL='"+player.getEmail()+"', TEMAJA='"+player.getTopicNamePlayer()+"' where FELHASZNALONEV='"+ player.getUserName()+"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateTopic(Topic topic) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE TEMA set NEV='"+ topic.getTopicName() + "' where NEV='"+ topic.getTopicName() + "'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateSubtopic(Subtopic subtopic) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE ALTEMA set NEV='"
                    + subtopic.getSubtopicName() + "', LEIRAS='" + subtopic.getDescription() + "', TEMA='" + subtopic.getTopicNameSubtopic() + "' where NEV='"+ subtopic.getSubtopicName() + "'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateQuiz(Quiz quiz) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE QUIZ set QUIZID="
                    + quiz.getQuizId() + ", QUIZTEMA='" + quiz.getTopicName() + "' where QUIZID=" + quiz.getQuizId() + "";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateQuestion(Question question) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE KERDES set ID="
                    + question.getId() + ", KERDESTARTALMA='" + question.getQuestionContent() +"', ALTEMA='" + question.getSubtopicNameQuestion() + "' where ID=" + question.getId();
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateAnswer(Answer answer) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE VALASZ set VALASZID="
                    + answer.getAnswerId() + ", KERDESID=" + answer.getQuestionId() +", VALASZTARTALMA='" + answer.getAnswerContent() + "', HELYESE='" + answer.getCorrectAnswer() + "' where VALASZID=" + answer.getAnswerId();
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updateAsk(Ask ask) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE FELTESZI set KERDES="
                    + ask.getQuestionIdAsk() + ", QUIZ=" + ask.getQuizIdAsk() + " where KERDES=" + ask.getQuestionIdAsk() + ", QUIZ=" + ask.getQuizIdAsk();
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void updatePlay(Play play) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE JATSZIK set FELHASZNALO= '"
                    + play.getUserNamePlay() + "', QID=" + play.getQuizIdPlay() + " where FELHASZNALO= '" + play.getUserNamePlay() + "', QID=" + play.getQuizIdPlay();
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateRankingPoints(RankingPoint rankingPoint) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE PONTSZAM set PID="
                    + rankingPoint.getIdRankingPoint() + ", FNEV= '" + rankingPoint.getUserNameRankingPoint() + "', TNEV= '"+ rankingPoint.getTopicNameRankingPoint() + "', RANGSORPONTSZAM= " + rankingPoint.getRankingPoint() + " where PID=" + rankingPoint.getIdRankingPoint();
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public Map<String, Integer> listMostFrequentQuestionsPlayedUser(String userName) throws SQLException {
        Map<String, Integer> mostFrequentQuestions = new TreeMap<String, Integer>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT KERDES.KERDESTARTALMA, COUNT(*) AS GYAKORISAG FROM KERDES " +
                    "JOIN FELTESZI ON KERDES.ID = FELTESZI.KERDES " +
                    "JOIN JATSZIK ON JATSZIK.QID = FELTESZI.QUIZ " +
                    "WHERE JATSZIK.FELHASZNALO = '" + userName +
                    "'GROUP BY KERDES.KERDESTARTALMA " +
                    "ORDER BY GYAKORISAG DESC;";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                mostFrequentQuestions.put(rs.getString(1), rs.getInt(2));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return mostFrequentQuestions;
    }

    public Map<String, Float> ranking() throws SQLException {
        Map<String, Float> rankings = new TreeMap<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT JATEKOS.FELHASZNALONEV, SUM(PONTSZAM.RANGSORPONTSZAM) / COUNT(*) AS ATLAGPONTSZAM FROM JATEKOS " +
                    "INNER JOIN PONTSZAM ON JATEKOS.FELHASZNALONEV = PONTSZAM.FNEV " +
                    "GROUP BY JATEKOS.FELHASZNALONEV " +
                    "ORDER BY ATLAGPONTSZAM DESC;";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                rankings.put(rs.getString(1), rs.getFloat(2));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return rankings;
    }

    public List<RankingByTopic> rankingByTopic() throws SQLException {
        List<RankingByTopic> rankingByTopics = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT JATEKOS.FELHASZNALONEV, TEMA.NEV, SUM(PONTSZAM.RANGSORPONTSZAM) / COUNT(*) AS ATLAGPONTSZAM FROM JATEKOS " +
                    "INNER JOIN PONTSZAM ON JATEKOS.FELHASZNALONEV = PONTSZAM.FNEV " +
                    "INNER JOIN TEMA ON PONTSZAM.TNEV = TEMA.NEV " +
                    "GROUP BY JATEKOS.FELHASZNALONEV, TEMA.NEV " +
                    "ORDER BY TEMA.NEV ASC, ATLAGPONTSZAM DESC";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                rankingByTopics.add(new RankingByTopic(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return rankingByTopics;
    }

    public List<String> questionsOfPlayedQuiz(int quizID) throws SQLException {
        List<String> questions = new ArrayList<>();
        rs = null;
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT KERDES.KERDESTARTALMA FROM QUIZ " +
                    "JOIN FELTESZI ON FELTESZI.QUIZ = QUIZ.QUIZID " +
                    "JOIN KERDES ON KERDES.ID = FELTESZI.KERDES " +
                    "WHERE QUIZ.QUIZID = " + quizID;
            rs = statement.executeQuery(sql);
            while(rs.next()){
                questions.add(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return questions;
    }

    public List<Integer> playedQuizId(Player player) throws SQLException {
        List<Integer> quizIds = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT JATSZIK.QID FROM JATSZIK " +
                    "WHERE JATSZIK.FELHASZNALO = '" + player.getUserName() + "'";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                quizIds.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return quizIds;
    }

    public List<SubtopicDescByTopic> subtopicDescriptionByTopic() throws SQLException {
        List<SubtopicDescByTopic> results = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT TEMA.NEV, ALTEMA.NEV, ALTEMA.LEIRAS FROM TEMA " +
                    "JOIN ALTEMA ON TEMA.NEV = ALTEMA.TEMA";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                results.add(new SubtopicDescByTopic(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return results;
    }

    public List<String> playersWithBigRankingPoints() throws SQLException {
        List<String> users = new ArrayList<>();
        rs = null;
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT JATEKOS.FELHASZNALONEV FROM JATEKOS " +
                    "JOIN PONTSZAM ON JATEKOS.FELHASZNALONEV = PONTSZAM.FNEV " +
                    "WHERE PONTSZAM.RANGSORPONTSZAM > 90;";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                users.add(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return users;
    }

}