package int242.jpadao;

import int242.jpadao.entities.Actor;
import int242.jpadao.repositories.ActorRepository;

// อย่าลืม Import คลาสเหล่านี้ด้วยนะครับ ไม่งั้นเดี๋ยวจะมี Error หา List หรือ Actor ไม่เจอ
import java.util.List;
import java.util.Scanner;
// import int242.jpadao.entities.Actor; // สมมติว่าไฟล์ Actor อยู่ที่นี่ (เอาคอมเมนต์ออกและแก้ Path ให้ตรงโปรเจกต์ของคุณ)

public class TestActor { // หรือคลาส TestActor ตามชื่อไฟล์ของคุณ

    public static void main(String[] args) {
        ActorRepository actorRepository = new ActorRepository();
        Scanner scanner = new Scanner(System.in); // สร้าง Scanner แค่ครั้งเดียวพอครับ

        while (true) {
            System.out.println("--Main Menu----------");
            System.out.println("1) Create new actor");
            System.out.println("2) Find actor by id");
            System.out.println("3) Delete actor by id");
            System.out.println("4) List all actor");
            System.out.println("0) Exit");
            System.out.println("---------------------");
            System.out.print("Enter your choice: ");

            Integer choice = scanner.nextInt();
            System.out.println("----------------");

            // ตัวอย่างการเรียกใช้ listAllActor เมื่อกด 4
            if (choice == 4) {
                listAllActor(actorRepository);
            } else if (choice == 0) {
                System.out.println("Exiting...");
                break; // กด 0 ให้ออกจากลูป
            }
        } // ปิด while
    } // ปิด main

    // ย้าย Method นี้ออกมาไว้ "นอก" main แต่ยังอยู่ "ใน" class ครับ
    private static void listAllActor(ActorRepository actorRepository) {
        List<Actor> actorList = actorRepository.findAll();
        for (Actor actor : actorList) {
            System.out.println(actor);
        }
    }

} // ปิด class