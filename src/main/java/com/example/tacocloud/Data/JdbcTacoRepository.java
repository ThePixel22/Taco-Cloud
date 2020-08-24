package com.example.tacocloud.Data;

import com.example.tacocloud.Models.Ingredient;
import com.example.tacocloud.Models.Taco;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    public JdbcTacoRepository (JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    @Override

    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(String ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private void saveIngredientToTaco(String ingredient, long tacoId) {
        jdbc.update(
                "insert into Taco_Ingredients (taco, ingredient) " +
                        "values (?, ?)",
                tacoId, ingredient);
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreateAt(new Date());
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreatorFactory(
                "insert into Taco (name, create_At) values  (?,?)",
                Types.VARCHAR, Types.TIMESTAMP)
                .newPreparedStatementCreator(Arrays.asList(taco.getName(),
                new Timestamp(taco.getCreateAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
