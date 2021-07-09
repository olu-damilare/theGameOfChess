package game.pieces.king.observers;

import game.board.Board;
import game.pieces.king.King;

public class KingObserver {

    private final PawnObserver pawnObserver;
    private final RookObserver rookObserver;
    private final KnightObserver knightObserver;
    private final BishopObserver bishopObserver;

    public KingObserver() {
        this.pawnObserver = new PawnObserver();
        this.rookObserver = new RookObserver();
        this.knightObserver = new KnightObserver();
        this.bishopObserver = new BishopObserver();
    }

    public void scanForPawnCheck(Board board, King king) {
        pawnObserver.scanForCheck(board, king);
    }

    public void scanForBishopCheck(Board board, King king){
       bishopObserver.scanForCheck(board, king);
    }

    public void scanForKnightCheck(Board board, King king){
        knightObserver.scanForCheck(board, king);
    }

    public void scanForRookCheck(Board board, King king) {
           rookObserver.scanForCheck(board, king);
    }



}

