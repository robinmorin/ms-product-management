package org.test.capitole.core.entity;


public class BrandDomain {

    private final Integer brandId;
    private final String brandName;

    public BrandDomain(Integer brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public static BrandBuilder builder() {
        return new BrandBuilder();
    }

    public static class BrandBuilder {
        private Integer brandId;
        private String brandName;

        public BrandDomain.BrandBuilder brandId(Integer brandId) {
            this.brandId = brandId;
            return this;
        }

        public BrandDomain.BrandBuilder brandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public BrandDomain build() {
            return new BrandDomain(brandId, brandName);
        }

    }

}
