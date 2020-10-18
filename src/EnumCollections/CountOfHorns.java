package EnumCollections;

public enum CountOfHorns {
    one,
    three,
    two;

    public static CountOfHorns getCount(int digit) {
        switch (digit) {
            case 0:
                return CountOfHorns.one;
            case 1:
                return CountOfHorns.two;
            case 2:
                return CountOfHorns.three;
        }
        return null;
    }
}
