import hello.MyException;
import org.junit.Test;

public class AssertTest {

    @Test
    public void t1(){
        int x = MyException.f(3);
        assert x > 0 : "测试未通过";
    }
}
