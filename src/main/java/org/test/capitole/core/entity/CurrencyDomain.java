package org.test.capitole.core.entity;


public class CurrencyDomain {

    private final String currencyIso;
    private final String currencyName;

    public CurrencyDomain(String currencyIso, String currencyName) {
        this.currencyIso = currencyIso;
        this.currencyName = currencyName;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public static CurrencyDomainBuilder builder() {
        return new CurrencyDomainBuilder();
    }

    public static class CurrencyDomainBuilder {
        private String currencyIso;
        private String currencyName;

        public CurrencyDomainBuilder currencyIso(String currencyIso) {
            this.currencyIso = currencyIso;
            return this;
        }

        public CurrencyDomainBuilder currencyName(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }

        public CurrencyDomain build() {
            return new CurrencyDomain(currencyIso, currencyName);
        }

    }

}
