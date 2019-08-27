package krystian.adamczyk.serviceTest;

import org.junit.Test;

public class TestSth {

  @Test
  public void testSth(){
    Boolean a = false;
    if(a==null){
      System.out.println("a is null");
    }else if(a){
      System.out.println("a is true");
    }
    else{
      System.out.println("a is false");
    }

  }
}
