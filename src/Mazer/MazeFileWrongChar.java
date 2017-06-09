package Mazer;

/**
 * Created by PC on 09/06/2017.
 */
public class MazeFileWrongChar extends MazeException {

    private int rown;
    private int coln;
    private String message = "Caráter inválido encontrado na posição: " + rown + coln;

    public String getMessage(){
        return message;
    }

    public MazeFileWrongChar(int row, int col){
        this.rown = row;
        this.coln = col;
    }
}
