package project_si;


public enum Coding {
    NKB((byte)0),
    GRAY((byte)1);

    private final byte kind;

    Coding(byte aKind) {
        kind = aKind;
    }

    byte getKind() {
        return kind;
    }

}
