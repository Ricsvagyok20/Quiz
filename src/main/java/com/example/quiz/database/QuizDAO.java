package com.example.quiz.database;

import com.example.quiz.modules.*;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                Player tmp = new Player(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5));
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
    public List<Belong> getBelongs() {
        List<Belong> belongs = new ArrayList<>();
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM TARTOZIK";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Belong tmp = new Belong(rs.getInt(1), rs.getInt(2));
                belongs.add(tmp);
                //System.out.println(belongs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return belongs;
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
                        "VALUES ('"+player.getUserName()+"', '"+player.getPassword()+"', '"+player.getEmail()+"', "+player.getRankingPoints()+", "+null+")";
            }
            else{
            sql = "INSERT INTO JATEKOS " +
                    "VALUES ('"+player.getUserName()+"', '"+player.getPassword()+"', '"+player.getEmail()+"', "+player.getRankingPoints()+", '"+player.getTopicNamePlayer()+"')";
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

    public void addBelong(Belong belong) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "INSERT INTO TARTOZIK " +
                    "VALUES ("+belong.getQuizIdBelong()+", "+belong.getAnswerIdBelong()+")";
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
            String sql = "DELETE FROM KERDES where ID='"+ questionId+"'";
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
            String sql = "DELETE FROM QUIZ where QUIZID='"+ quizId+"'";
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
            String sql = "DELETE FROM VALASZ where VALASZID='"+ answerId+"'";
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
            String sql = "DELETE FROM FELTESZI where KERDES='"+ quiestionId+"', QUIZ='"+ quizID +"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deletePlay(int playerId, int quizID) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM JATSZIK where FELHASZNALO='"+ playerId+"', QUIZ='"+ quizID +"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteBelong(int quizId, int answerId) throws SQLException {
        try{
            conn = DAO();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM TARTOZIK where KID='"+ quizId+"', VID='"+ answerId +"'";
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
                    +player.getPassword()+"', EMAIL='"+player.getEmail()+"', RANGSORPONTSZAM='"+player.getRankingPoints()+"', TEMAJA='"+player.getTopicNamePlayer()+"' where FELHASZNALONEV='"+ player.getUserName()+"'";
            rs = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}