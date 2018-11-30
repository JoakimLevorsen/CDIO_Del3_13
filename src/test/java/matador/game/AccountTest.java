package matador.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void increase() {
        //Positiv test
        matador.game.Account konto = new matador.game.Account(10);
        konto.increase(2);
        assertEquals(12, konto.getBalance());

        //Negativ test
        matador.game.Account konto2 = new matador.game.Account(10);
        konto2.increase(-2);
        assertNotEquals(8, konto2.getBalance());
    }

    @Test
    public void deduct() {
        //Positiv test
        matador.game.Account konto = new matador.game.Account(10);
        konto.deduct(2);
        assertEquals(8,konto.getBalance());

        //Negativ test
        matador.game.Account konto2 = new matador.game.Account(10);
        konto2.deduct(-2);
        assertNotEquals(12, konto2.getBalance());
    }
}
