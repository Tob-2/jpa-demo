package sit.int224;

import sit.int224.dao.impl.FilmDao;
import sit.int224.entities.Film;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        FilmDao filmDao = new FilmDao();

        // ---------------------------------------------------------
        // 1. CREATE (ทดสอบการเพิ่มข้อมูล)
        // ---------------------------------------------------------
        System.out.println("--- 1. Testing CREATE (Save) ---");
        Film newFilm = new Film(0, "The Matrix", "A computer hacker learns about the true nature of his reality.", "1999", "R");
        filmDao.save(newFilm);
        System.out.println("Saved new film: " + newFilm.getTitle() + "\n");
        // หมายเหตุ: เนื่องจากคำสั่ง INSERT ใน DAO ไม่ได้เอาค่า ID ไปใช้ (มักจะใช้ Auto Increment ใน Database)
        // ดังนั้นตอนสร้างเลยใส่เลข 0 หรืออะไรไปก่อนก็ได้ครับ

        // ---------------------------------------------------------
        // 2. READ (ทดสอบการดึงข้อมูลทั้งหมด)
        // ---------------------------------------------------------
        System.out.println("--- 2. Testing READ (Find All) ---");
        System.out.println("Total films in DB: " + filmDao.findAll().size());
        // พิมพ์ออกมาดูแค่ 3 เรื่องแรกเพื่อไม่ให้ Console รกจนเกินไป
        filmDao.findAll().stream().limit(3).forEach(System.out::println);
        System.out.println("\n");

        // ---------------------------------------------------------
        // 3. READ (ทดสอบการดึงข้อมูลตาม ID)
        // ---------------------------------------------------------
        System.out.println("--- 3. Testing READ (Find by ID) ---");
        int testId = 1; // สมมติว่าในฐานข้อมูลของคุณมีหนัง ID=1 อยู่ (ฐานข้อมูล Sakila มักจะมี)
        Optional<Film> foundFilm = filmDao.find(testId);

        if (foundFilm.isPresent()) {
            System.out.println("Found Film ID " + testId + ": " + foundFilm.get().getTitle());
        } else {
            System.out.println("Film ID " + testId + " not found!");
        }
        System.out.println("\n");

        // ---------------------------------------------------------
        // 4. UPDATE (ทดสอบการแก้ไขข้อมูล)
        // ---------------------------------------------------------
        System.out.println("--- 4. Testing UPDATE ---");
        int updateId = 2; // สมมติว่าจะลองอัปเดตหนัง ID=2 (ถ้าไม่มีให้เปลี่ยนเป็นเลขที่มีใน DB ครับ)
        // สร้าง Object ใหม่โดยใช้ ID ที่ต้องการแก้ พร้อมกับข้อมูลใหม่
        Film updatedFilmInfo = new Film(updateId, "Updated Title", "This is an updated description.", "2024", "PG-13");

        filmDao.update(updatedFilmInfo);

        // ลองดึงมาดูอีกรอบว่าเปลี่ยนจริงไหม
        Optional<Film> checkUpdate = filmDao.find(updateId);
        if (checkUpdate.isPresent()) {
            System.out.println("After Update ID " + updateId + ": " + checkUpdate.get().getTitle() + " - " + checkUpdate.get().getDescription());
        }
        System.out.println("\n");

        // ---------------------------------------------------------
        // 5. DELETE (ทดสอบการลบข้อมูล)
        // ---------------------------------------------------------
        System.out.println("--- 5. Testing DELETE ---");
        int deleteId = 999; // กำหนด ID ที่ต้องการลบ (ควรระวัง! ใส่ ID ที่สร้างมาเทสเท่านั้น อย่าเผลอลบข้อมูลจริงนะครับ)
        filmDao.delete(deleteId);
        System.out.println("Executed delete for Film ID: " + deleteId);

    } // ปิด Method main
} // ปิด Class Test