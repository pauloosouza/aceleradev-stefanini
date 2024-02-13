package br.com.projeto.api.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.api.models.Customer;
import br.com.projeto.api.models.Product;
import br.com.projeto.api.models.Sale;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class ReadXLSXFileService {
  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductService productService;

  @Autowired
  private SaleService saleService;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final int CPF_COLUMN_INDEX = 0;
  private static final int PRODUCT_CODE_COLUMN_INDEX = 1;
  private static final int QUANTITY_COLUMN_INDEX = 2;
  private static final int DATE_COLUMN_INDEX = 3;

  public String run(MultipartFile file) throws IOException {
    try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
      Sheet sheet = workbook.getSheetAt(0);
      processSheet(sheet);
    } catch (IOException e) {
      throw new IOException("Erro ao processar o arquivo: " + e.getMessage(), e);
    }

    return "Arquivo processado com sucesso!";
  }

  private void processSheet(Sheet sheet) {
    DataFormatter dataFormatter = new DataFormatter();

    for (Row row : sheet) {
      if (row.getRowNum() == 0)
        continue;

      String cpf = dataFormatter.formatCellValue(row.getCell(CPF_COLUMN_INDEX));
      String productCode = dataFormatter.formatCellValue(row.getCell(PRODUCT_CODE_COLUMN_INDEX));
      String quantityStr = dataFormatter.formatCellValue(row.getCell(QUANTITY_COLUMN_INDEX));
      String dateString = dataFormatter.formatCellValue(row.getCell(DATE_COLUMN_INDEX));

      int quantity = !quantityStr.trim().isEmpty() ? Integer.parseInt(quantityStr) : 0;
      LocalDate date = parseDate(dateString);

      if (cpf != null && productCode != null && quantity > 0 && date != null) {
        processSale(cpf, productCode, quantity, date);
      }
    }
  }

  private LocalDate parseDate(String dateStr) {
    try {
      return LocalDate.parse(dateStr, DATE_FORMATTER);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  private void processSale(String cpf, String productCode, int quantity, LocalDate date) {
    Customer customer = customerService.searchCustomerByCpf(cpf);
    Product product = productService.searchProductByCode(productCode);

    if (customer != null && product != null) {
      Sale sale = new Sale();
      sale.setCustomer(customer);
      sale.addProduct(product, quantity);
      sale.setDate(date);

      saleService.create(sale);
    }
  }
}
