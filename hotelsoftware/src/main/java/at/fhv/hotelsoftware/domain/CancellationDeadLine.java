package at.fhv.hotelsoftware.domain;

import java.time.LocalDateTime;

public class CancellationDeadLine {
    LocalDateTime localDateTime;

    public CancellationDeadLine(){};
    public CancellationDeadLine(LocalDateTime localDateTime){
        this.localDateTime = localDateTime;
    }
}
