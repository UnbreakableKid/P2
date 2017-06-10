package Mazez;

public class MazeFileWrongChar extends MazeException {

    private int rown;
    private int coln;
    private String message = "Caráter inválido encontrado na posição: ";

    public String getMessage(){
        return message + rown + coln;
    }

    public MazeFileWrongChar(int row, int col){
        this.rown = row;
        this.coln = col;
    }
}
