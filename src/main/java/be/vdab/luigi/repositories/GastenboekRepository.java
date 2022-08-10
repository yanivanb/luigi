package be.vdab.luigi.repositories;

import be.vdab.luigi.domain.Gast;
import be.vdab.luigi.domain.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class GastenboekRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public GastenboekRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template).withTableName("gastenboek").usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Gast> gastMapper =
            (result, rowNum) ->
                    new Gast(result.getLong("id"), LocalDate.parse(result.getString("gastdatum")),
                            result.getString("gastnaam"), result.getString("gastbericht"));

    public long create(Gast gast) {
        return insert.executeAndReturnKey(
                        Map.of("gastnaam", gast.getNaam(),
                                "gastnaam", gast.getDatum(),
                                "gastbericht", gast.getBericht()))
                .longValue();
    }

    public List<Gast> findAll() {
        var sql = """
        select id, gastnaam, gastdatum, gastbericht
        from gastenboek
        order by id DESC
        """;
        return template.query(sql, gastMapper);
    }


}
