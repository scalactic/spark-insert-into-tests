import org.apache.spark.sql.{DataFrame, SaveMode}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class InsertIntoTests extends AnyFlatSpec with SparkSpec {
    import InsertIntoTests._
    import spark.implicits._

    val a: Vector[Person] = Vector(Person(1, "a", "2024-06-01"))
    val b: Vector[Person] = Vector(Person(2, "b", "2024-06-02"))
    val c: Vector[Person] = Vector(Person(3, "c", "2024-06-03"))
    val d: Vector[Person] = Vector(Person(4, "d", "2024-06-04"))

    val dfA: DataFrame = a.toDF
    val dfB: DataFrame = b.toDF
    val dfC: DataFrame = c.toDF
    val dfD: DataFrame = d.toDF
    val dfBC: DataFrame = b.++(c).toDF

    it should "create table " in {
        spark.sql("CREATE TABLE person(id INT, name STRING, rptg_dt STRING) using PARQUET PARTITIONED BY(rptg_dt)")
    }

    it should "insert dfA to person table" in {
        dfA.write.mode(SaveMode.Overwrite).insertInto("person")
        spark.table("person").show(truncate = false)
        spark.table("person").count shouldBe 1
    }

    it should "insert dfBC to person table without losing dfA" in {
        dfBC.write.mode(SaveMode.Overwrite).insertInto("person")
        spark.table("person").show(truncate = false)
        spark.table("person").count shouldBe 3
    }
}

object InsertIntoTests {
    case class Person(id: Int, name: String, rptg_dt: String)
}