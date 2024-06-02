import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

trait HmsSpec extends LazyLogging {

    def oneLine(replaced: String, str: String = ""): String =
        if (replaced != str) oneLine(replaced.replace("\n", "").replaceAll(" {2}", " "), replaced) else str.strip


    def createAllTables(implicit spark: SparkSession): Unit = new java.io.File(getClass.getResource("/hms").getPath)
        .listFiles
        .foreach { file =>
            val src = Source.fromFile(new java.io.File(file.getPath))
            val tableScript = oneLine(src.getLines().mkString)
            logger.info(s"executing script: $tableScript")
            spark.sql(tableScript)
            src.close()
        }

    def dropAllTables(implicit spark: SparkSession): Unit = {
        spark.sql("show tables").show(truncate = false)
        spark.sql("show tables").select("tableName").collect.toList.map {
            case Row(tableName: String) => tableName
        }.foreach(table => spark.sql(s"drop table $table"))
        spark.sql("show tables").show(truncate = false)
    }
}
