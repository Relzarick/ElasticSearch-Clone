package tokenizer;

public class Tokenizer {
    private final TokenStrategy strat;

    public Tokenizer(TokenStrategy strat) {
        this.strat = strat;
    }

    public void tokenize(String inputs) {
        System.out.println(strat.toTokens(inputs));
    }

}