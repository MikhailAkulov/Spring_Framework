package ru.gb.myspringdemo.aspect;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.myspringdemo.aspect.RecoverException;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TestService {

    public void testMethod() throws Exception {
        String s1 = "Истина где-то рядом";

        getError();

        System.out.println(s1);
    }

    private void getError() throws Exception {
        throw new Exception("error");
    }

    @RecoverException(noRecoverFor = {RuntimeException.class, NoSuchElementException.class})
    public void catchIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

    @RecoverException(noRecoverFor = {NullPointerException.class})
    public void catchNullPointerException() {
        throw new NullPointerException();
    }
}
