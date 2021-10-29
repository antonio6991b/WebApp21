package ru.bolgov.soulbeer.model.dto.report;


import ru.bolgov.soulbeer.model.entity.ProductReport;

public class ProductReportDto {

   private ProductReport productReport;

   private String productName;

   public ProductReport getProductReport() {
      return productReport;
   }

   public void setProductReport(ProductReport productReport) {
      this.productReport = productReport;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }
}
