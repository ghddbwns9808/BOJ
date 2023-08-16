import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Othello game = new Othello();
        int n = Integer.parseInt(br.readLine());
        while(n-->0) {
            String[] command = br.readLine().split(" ");
            game.setStone(Integer.parseInt(command[0]), Integer.parseInt(command[1]));
        }
        game.printResult();
    }
}

class Othello{
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private char[][] board;
    private boolean isBlackTurn;
    private int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public Othello() {
        initGame();
    }

    private void initGame() {
        this.board = new char[7][7];
        for(int i=0; i<7; i++)
            Arrays.fill(this.board[i], '.');

        board[3][3] = 'W'; board[4][4] = 'W';
        board[3][4] = 'B'; board[4][3] = 'B';

        this.isBlackTurn = true;
    }

    private void switchTurn() {
        this.isBlackTurn = !isBlackTurn;
    }

    public void setStone(int x, int y) {
        if(isBlackTurn)
            this.board[x][y] = 'B';
        else
            this.board[x][y] = 'W';

        switchStones(x, y);

        switchTurn();
    }

    private void switchStones(int x, int y) {
        Stack<Position> tempStones = new Stack();
        char targetColor;
        char myColor;
        if(isBlackTurn) {
            targetColor = 'W';
            myColor = 'B';
        }
        else {
            targetColor = 'B';
            myColor = 'W';
        }

        for(int i=0; i<8; i++) {
            //뒤집을 수 있는지 확인할 플래그 변수
            boolean canSwitch = false;
            //인접 8개 칸 중 상대방 색깔의 돌이고 유효한 위치일 때 그 방향으로 쭉 탐색
            if(isValidatePosition(x+dx[i], y+dy[i]) && board[x+dx[i]][y+dy[i]] == targetColor) {
                tempStones.add(new Position(x+dx[i], y+dy[i]));

                while(true) {
                    Position cur = tempStones.peek();
                    //유효한 위치
                    if(isValidatePosition(cur.x + dx[i], cur.y + dy[i])) {
                        //해당 위치가 타겟 컬러와 일치?
                        if(board[cur.x + dx[i]][cur.y + dy[i]] == targetColor) {
                            //스택에 넣기
                            tempStones.add(new Position(cur.x + dx[i], cur.y + dy[i]));
                        }
                        //내 컬러와 동일한 컬러를 만났다? 지금까지 놓여있던 돌 다 뒤집기
                        else if(board[cur.x + dx[i]][cur.y + dy[i]] == myColor) {
                            canSwitch = true;
                            break;
                        }
                        //아무것도 없는 지점에 도달?
                        else {
                            break;
                        }
                    }
                    //더이상 못가는 지점
                    else {
                        break;
                    }
                }
                //뒤집어야할 돌이 있다?
                if(canSwitch) {
                    while(!tempStones.isEmpty()) {
                        Position stone = tempStones.pop();
                        board[stone.x][stone.y] = myColor;
                    }
                }
                tempStones.clear();
            }
        }
    }

    private boolean isValidatePosition(int x, int y) {
        if(0<x && x<7 && 0<y && y<7)
            return true;
        return false;
    }

    private void printBoard() throws IOException {
        for(int i=1; i<7; i++) {
            for(int j=1; j<7; j++)
                bw.write(this.board[i][j]);
            bw.write('\n');
        }
    }

    public void printResult() throws IOException {
        int black=0;
        int white=0;
        for(int i=1; i<7; i++){
            for(int j=0; j<7; j++){
                if (board[i][j] == 'B')
                    black++;
                else if (board[i][j] == 'W') {
                    white++;
                }
            }
        }
        this.printBoard();
        if (black > white)
            bw.write("Black\n");
        else
            bw.write("White\n");
        bw.flush();
        bw.close();
    }
}

class Position{
    int x;
    int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}