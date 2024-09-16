package dz.izemaghilas.cmd;

public class DuplicateCmdOptionException extends Exception {
    private final String cmdName;
    private final String duplicatedCmdOption;

    public DuplicateCmdOptionException(String cmdName, String duplicatedCmdOption) {
        super();
        this.cmdName = cmdName;
        this.duplicatedCmdOption = duplicatedCmdOption;
    }

    @Override
    public String toString() {
        return String.format("pwcli: '%s' should have only one '%s' option. Try 'pwcli help' for more information", this.cmdName, this.duplicatedCmdOption);
    }
}
