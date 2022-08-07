package be.vdab.luigi.forms;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
public record VanTotPrijsForm(@NotNull @PositiveOrZero BigDecimal van,
                              @NotNull @PositiveOrZero BigDecimal tot) {
}