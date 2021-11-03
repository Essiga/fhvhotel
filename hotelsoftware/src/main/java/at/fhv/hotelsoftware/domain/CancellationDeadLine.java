package at.fhv.hotelsoftware.domain;

import java.time.LocalDateTime;

public class CancellationDeadLine {
    LocalDateTime cancellationDeadLine;

    public CancellationDeadLine(){};
    public CancellationDeadLine(LocalDateTime localDateTime){
        this.cancellationDeadLine = localDateTime;
    }
}
