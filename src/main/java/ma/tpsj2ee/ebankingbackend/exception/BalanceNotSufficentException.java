package ma.tpsj2ee.ebankingbackend.exception;

public class BalanceNotSufficentException extends Exception {
    public BalanceNotSufficentException(String message) {
        super(message);
    }
}
