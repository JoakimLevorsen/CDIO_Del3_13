package matador.cards;

abstract public class ChanceCard {

    abstract public void process();

    /*
    Jeg prøvede med constructoren under, og det fuckede testen op. Men Det kan være, i har mere held!
    Nu har I ihvertfald en cardController der fungerer... jeg mener KAN fungere i teorien ihvertfald.

    // TODO: Lav en constructor der bare throw´er en fejl
    ChanceCard() {
        throw new Error("ChanceCard constructor was called. ChanceCards need a type.");

    }
    // TODO: Implementer funktionen process der er detailjeret i design klasse diagrammet, dog her skal den bare throwe en fejl der siger noget alla 'ChanceCard skal aldrig bruges i spillet'
    public void process(){
        throw new Error("ChanceCard.process() was called. ChanceCards need a type.");
    }
    */

}
