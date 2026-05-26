package sit.int224.dao.impl;

import sit.int224.dao.interfaces.JdbcDao;
import sit.int224.entities.Film;
import sit.int224.jdbc.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FilmDao implements JdbcDao<Film, Integer> {
    // กำหนดคำสั่ง SQL ให้ครบทุกการทำงาน
    private final static String SELECT_ALL = "SELECT * FROM film";
    private final static String SELECT_BY_ID = "SELECT * FROM film WHERE film_id = ?";
    private final static String INSERT_FILM = """
            INSERT INTO film (title, release_year, rating, language_id) 
            VALUES (?, ?, ?, ?)
            """;
    private final static String UPDATE_FILM = """
            UPDATE film SET title = ?, description = ?, release_year = ?, rating = ? 
            WHERE film_id = ?
            """;
    private final static String DELETE_FILM = "DELETE FROM film WHERE film_id = ?";

    private Film mapRowToEntity(ResultSet rs) throws SQLException {
        return new Film(rs.getInt("film_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("release_year"),
                rs.getString("rating"));
    }

    @Override
    public void update(Film film) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement(UPDATE_FILM);
            // เซ็ตค่าตามลำดับเครื่องหมาย ? ในคำสั่ง UPDATE_FILM
            pstm.setString(1, film.getTitle());
            pstm.setString(2, film.getDescription());
            pstm.setString(3, film.getReleaseYear());
            pstm.setString(4, film.getRating());
            pstm.setInt(5, film.getId()); // *หมายเหตุ: ถ้าคลาส Film ของคุณตั้งชื่อ getter ว่า getFilmId() ให้เปลี่ยนตรงนี้ด้วยครับ

            pstm.executeUpdate();

            pstm.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement(DELETE_FILM);
            pstm.setInt(1, id); // ระบุ ID ที่ต้องการลบ

            pstm.executeUpdate();

            pstm.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Film> find(Integer id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement(SELECT_BY_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            Optional<Film> result = Optional.empty();
            // ถ้าหาข้อมูลเจอ rs.next() จะเป็น true
            if (rs.next()) {
                result = Optional.of(mapRowToEntity(rs));
            }

            rs.close();
            pstm.close();
            connection.close();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new LinkedList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(SELECT_ALL);
            while (rs.next()) {
                films.add(mapRowToEntity(rs));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }

    @Override
    public void save(Film film) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement(INSERT_FILM);
            pstm.setString(1, film.getTitle());
            pstm.setString(2, film.getReleaseYear());
            pstm.setString(3, film.getRating());
            pstm.setString(4, "1");

            pstm.executeUpdate();

            pstm.close(); // เพิ่มการ close ให้สมบูรณ์
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}