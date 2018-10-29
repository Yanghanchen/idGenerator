package hnu.yhc.entity;

public class Id {
    private long seq;
    private long time;
    private long machine;

    public Id() {
    }

    public Id(long machine, long seq, long time) {
        super();
        this.machine = machine;
        this.seq = seq;
        this.time = time;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getMachine() {
        return machine;
    }

    public void setMachine(long machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return "Id{" +
                " seq=" + seq +
                ", time=" + time +
                ", machine=" + machine +
                " }";
    }
}
