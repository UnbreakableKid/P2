package Mazez;

public class MazeFileWrongChar extends MazeException {

    private int rown;
    private int coln;
    private String message = "Caráter inválido encontrado na posição: " + rown + coln;

    public String getMessage(){
        return message;
    }

    public MazeFileWrongChar(int col, int row){
        this.rown = row;
        this.coln = col;
    }
}
