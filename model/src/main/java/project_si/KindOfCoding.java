package project_si;

public enum KindOfCoding {
    NKB((byte)0),
    GRAY((byte)1);

    private final byte kind;

    KindOfCoding(byte aKind) {
        kind = aKind;
    }
}
