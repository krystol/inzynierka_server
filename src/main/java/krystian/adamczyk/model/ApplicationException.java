package krystian.adamczyk.model;

public class ApplicationException extends RuntimeException{
  public ApplicationException() {
  }

  public ApplicationException(String var1) {
    super(var1);
  }

  public ApplicationException(String var1, Throwable var2) {
    super(var1, var2);
  }

  public ApplicationException(Throwable var1) {
    super(var1);
  }

  protected ApplicationException(String var1, Throwable var2, boolean var3, boolean var4) {
    super(var1, var2, var3, var4);
  }
}
