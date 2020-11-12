package model;

public enum Flag {
    N, Z, V, C;

    private boolean flag = false;

    public void setFlag(boolean b) {
        flag = b;
    }
    public boolean isSet() {
        return flag;
    }
}
