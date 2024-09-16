package dz.izemaghilas.cmd;

public class EmptyCmdException extends Exception {
    public EmptyCmdException() {
        super();
    }

    @Override
    public String toString() {
        return "pwcli: try 'pwcli help' for more information";
    }
}
