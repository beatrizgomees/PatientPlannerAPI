



@ChangeLog(order = "001")
public class DatabaseChangelog {

  @ChangeSet(order = "001", id = "createMedicalSpecialtyCollection", author = "testAuthor")
  public void createMedicalSpecialtyCollection(DB db) {
    db.createCollection("medicalSpecialty");
  }
