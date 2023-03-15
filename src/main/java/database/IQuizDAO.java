package database;
import java.util.List;

public interface IQuizDAO {
    boolean Add( pancake);
    List<Pancake> Get();
    boolean Update(Pancake pancake);
    boolean Delete(Pancake pancake);
}
