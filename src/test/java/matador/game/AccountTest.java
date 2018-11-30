package matador.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void increase() {
        //Positiv test
        matador.game.Account konto = new matador.game.Account(1000);
        konto.increase(3000);
        assertEquals(4000, konto.getBalance());

        //Negativ test
        matador.game.Account konto2 = new matador.game.Account(1000);
        konto2.increase(-3000);
        assertNotEquals(-2000, konto2.getBalance());
    }

    @Test
    public void deduct() {
        //Positiv test
        matador.game.Account konto = new matador.game.Account(1000);
        konto.deduct(3000);
        assertEquals(0,konto.getBalance());

        //Negativ test
        matador.game.Account konto2 = new matador.game.Account(1000);
        konto2.deduct(-3000);
        assertNotEquals(4000, konto2.getBalance());
    }
}
