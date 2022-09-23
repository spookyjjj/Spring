package kr.co.greenart.model.car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryMySQL implements CarRepository{
	@Autowired
	private JdbcTemplate template;
	
	private CarMapper mapper = new CarMapper();
	public class CarMapper implements RowMapper<Car> {
		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("id");
			String model = rs.getString("model");
			int price = rs.getInt("price");
			return new Car(id, model, price);
		}
	}
	
	@Override
	public List<Car> getAll() {
		return template.query("select * from cars", mapper);
	}

	@Override
	public Car getById(int id) {
		return template.queryForObject("select * from cars where id=?", mapper, id);
		
	}

	@Override
	public int add(Car car) {
		return template.update("insert into cars (model, price) values (?, ?)", car.getModel(), car.getPrice());
	}

	@Override
	public int update(Car car) {
		return template.update("update cars set model=?, price=? where id=?", car.getModel(), car.getPrice(), car.getId());
	}

	@Override
	public int delete(int id) {
		return template.update("delete from cars where id=?", id);
	}

	@Override
	public int[] batchInsert(List<Car> list) {
		return template.batchUpdate("insert into cars (medel, price) values (?, ?)", 
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Car car = list.get(i);
						ps.setString(1, car.getModel());
						ps.setInt(2, car.getPrice());
					}
					@Override
					public int getBatchSize() { //몇번할건지~
						return list.size();
					}
				});
	}

}
