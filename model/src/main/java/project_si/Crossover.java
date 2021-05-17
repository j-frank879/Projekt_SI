package project_si;

public enum Crossover {
    UNIFORM((byte)0),
    ONE_POINT((byte)1),
    TWO_POINT((byte)2);

    private final byte kindOfCrossing;

    Crossover(byte aKindOfCrossing) {
        kindOfCrossing = aKindOfCrossing;
    }

    byte getKindOfCrossing() {
        return kindOfCrossing;
    }
}
