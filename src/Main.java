import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Scanner scanner = new Scanner(System.in);

        ArrayList<Teacher> teachers = new ArrayList<>();
        ArrayList<Abiturient> abiturients = new ArrayList<>();
//        ArrayList<Exam> exams = new ArrayList<>();
        ArrayList<Exam> exams = ReadObject();
        ArrayList<String> faculties = new ArrayList<>();
        Map<String, Double> map = new LinkedHashMap<>();


        int flag = 1;

        while (flag != 0) {
            System.out.println("1. Add teacher");
            System.out.println("2. Add abiturient");
            System.out.println("3. Add exam");
            System.out.println("4. Add faculty");
            System.out.println("5. Show best points");
            System.out.println("6. Writing in file");
            System.out.println("7. Reading from file");
            System.out.println("8. Show exams list");
            System.out.println("0. Exit");
            System.out.println("\n");

            flag = Integer.parseInt(reader.readLine());

            switch (flag) {
                case 1:
                    addTeacher(teachers);
                    System.out.println("Teachers list:");
                    for (var t:
                         teachers) {
                        System.out.println(t);
                    }
                    break;
                case 2:
                    if (faculties.size() < 1) {
                        System.out.println("One more faculty required. Please add faculty");
                    } else {
                        addAbiturient(abiturients, faculties);
                        System.out.println("Abiturients list:");
                        for (var a:
                                abiturients) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 3:
                    if (teachers.size() < 1 || faculties.size() < 1 || abiturients.size() < 1) {
                        System.out.println("Unable to take exam. Please add teachers, abiturietns or faculties");
                    } else {
                        addExam(exams, abiturients, teachers);
                        System.out.println("Exams list:");
                        for (var e:
                             exams) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 4:
                    addFaculty(faculties);
                    System.out.println("Faculties list:");
                    for (var f:
                            faculties) {
                        System.out.println(f);
                    }
                    System.out.println("\n");
                    break;
                case 5:
                    if (abiturients.size() < 5 && exams.size() < 3) {
                        System.out.println("five more abiturients required and three more exams required");
                    } else {
                        for (var a:
                             abiturients) {
                            map.put(a.getName(), getAverageMark(exams, a.getName()));
                        }

                        map = MapUtil.sortByValue(map);
                        System.out.println("best points list:");
                        System.out.println(map);
                    }

                    break;
                case 6:
                    WriteObject(exams);
                    System.out.println("writing in file is success");
                    break;
                case 7:
                    ReadObject();
                    System.out.println("reading from file is success");
                    break;
                case 8:
                    if (exams.isEmpty()) {
                        System.out.println("Exams list is empty");
                    } else {
                        System.out.println("Exams list:");
                        for (var e:
                                exams) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exit...");
                    break;
            }
        }
    }

    private static void addTeacher(ArrayList<Teacher> teachers) {
        teachers.add(new Teacher("Teacher_" + givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect()));
    }

    private static void addFaculty(ArrayList<String> faculties) {
        faculties.add("Faculty_" + givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect());
    }

    private static void addAbiturient(ArrayList<Abiturient> abiturients, ArrayList<String> faculties) {
        abiturients.add(new Abiturient(
                "Abiturient_" + givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(),
                getRandomElement(faculties)));
    }

    private static void addExam(ArrayList<Exam> exams, ArrayList<Abiturient> abiturients, ArrayList<Teacher> teachers) {
        Scanner scanner = new Scanner(System.in);


        int teacherIndex = -1;
        while (teacherIndex > teachers.size() || teacherIndex < 1) {
            System.out.println("Choose teacher:");
            for (int i = 0; i < teachers.size(); i++) {
                System.out.println(i + 1 + " - " + teachers.get(i));
            }
            teacherIndex = Integer.parseInt(scanner.nextLine());
        }
        teacherIndex--;


        int abiturientIndex = -1;
        while (abiturientIndex > abiturients.size() || abiturientIndex < 1) {
            System.out.println("Choose abiturient:");
            for (int i = 0; i < abiturients.size(); i++) {
                System.out.println(i + 1 + " - " + abiturients.get(i));
            }
            abiturientIndex = Integer.parseInt(scanner.nextLine());
        }
        abiturientIndex--;


        int mark = -1;
        while (mark < 0 || mark > 10) {
            System.out.println("Press exam mark:");
            mark = Integer.parseInt(scanner.nextLine());
        }



        exams.add(new Exam("Exam_" + givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(),
                abiturients.get(abiturientIndex),
                teachers.get(teacherIndex),
                mark));
    }

    private static double getAverageMark(ArrayList<Exam> exams, String name) {
        ArrayList<Integer> marks = new ArrayList<>();
        for (var e:
             exams) {
            if (e.getAbiturient().getName() == name) {
                marks.add(e.getMark());
            }
        }

        Double averageMark = 0.0;
        for (var m:
             marks) {
            averageMark += m;
        }

        return averageMark / marks.size();
    }

    private static String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static<T> T getRandomElement(ArrayList<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static void WriteObject(ArrayList<Exam> exams) throws IOException, ClassNotFoundException {
//        try {
//            FileOutputStream fos = new FileOutputStream("exam.bin");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//            oos.writeInt(exams.size());
//
//            for (Exam exam : exams) {
//                oos.writeObject(exam);
//            }
//
//            fos.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Сериализация в файл с помощью класса ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("exams.out"));
        objectOutputStream.writeObject(exams);
        objectOutputStream.close();
    }

    public static ArrayList<Exam> ReadObject() throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Востановление из файла с помощью класса ObjectInputStream
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("exams.out"));
        ArrayList<Exam> exams = (ArrayList<Exam>) objectInputStream.readObject();
        objectInputStream.close();

        return exams;
    }
}