package Mazer;

/**
 * Created by PC on 09/06/2017.
 */
public class MazeFileNumCols extends MazeException {

    private int coln;
    private String message = "Todas as linhas têm de ter o mesmo número de caracters. Erro encontrado na linha nº:" + coln;

    public String getMessage(){
        return message;
    }

    public MazeFileNumCols(int col){
        this.coln = col;
    }

}
