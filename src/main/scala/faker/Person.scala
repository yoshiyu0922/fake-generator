package faker

import java.io.File
import java.util.Locale

import com.github.javafaker.Faker
import com.github.tototoshi.csv.CSVWriter

object Person {
  def main(args: Array[String]): Unit = {
    val rowNum = args.head.toInt
    val persons = createPersonData(rowNum)
    createAddressData(persons)
  }

  private def createPersonData(rowNum: Int): Seq[Seq[Any]] = {
    val faker = new Faker(new Locale("ja"))
    val file = new File("./out/person.csv")
    val writer = CSVWriter.open(file)

    val header = List("id", "name", "age", "job_title")
    writer.writeRow(header)

    val result = (1 to rowNum)
      .map(
        _ =>
          Seq(
            faker.name().fullName(),
            faker.number().numberBetween(20, 100),
            faker.job().title()
        )
      )
      .distinct
      .zipWithIndex
      .map {
        case (list, i) => Seq(i + 1, list.head, list(1), list.last)
      }

    writer.writeAll(result)

    writer.close()
    result
  }

  private def createAddressData(persons: Seq[Seq[Any]]): Unit = {
    val faker = new Faker(new Locale("ja"))
    val file = new File("./out/address.csv")
    val writer = CSVWriter.open(file)

    val header = List("id", "person_id", "post_code", "prefecture", "city")
    writer.writeRow(header)

    (1 to persons.size)
      .grouped(10)
      .foreach(list => {
        val result = list
          .map(i => {
            Seq(
              i,
              i,
              faker.address().zipCode(),
              faker.address().state(),
              faker.address().city()
            )
          })
          .distinct
        writer.writeAll(result)
      })

    writer.close()
  }
}
