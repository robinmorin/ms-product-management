package org.test.capitole.core.entity;

public class ProductDomain {

    private Long productId;

    private String productName;

    private String sku;

    private String referencia;

    public ProductDomain(Long productId, String productName, String sku, String referencia) {
        this.productId = productId;
        this.productName = productName;
        this.sku = sku;
        this.referencia = referencia;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSku() {
        return sku;
    }

    public String getReferencia() {
        return referencia;
    }

    public static ProductDomainBuilder builder() {
        return new ProductDomainBuilder();
    }

    public static class ProductDomainBuilder {
        private Long productId;
        private String productName;
        private String sku;
        private String referencia;

        public ProductDomainBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public ProductDomainBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductDomainBuilder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public ProductDomainBuilder referencia(String referencia) {
            this.referencia = referencia;
            return this;
        }

        public ProductDomain build() {
            return new ProductDomain(productId, productName, sku, referencia);
        }

    }
}
