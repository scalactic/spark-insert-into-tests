import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.internal.SQLConf.PARTITION_OVERWRITE_MODE
import org.apache.spark.sql.internal.StaticSQLConf.WAREHOUSE_PATH

import scala.reflect.io.Path

trait SparkSpec {
    val testRoot = "/tmp/spark"

    def configureSparkSession: SparkSession.Builder = SparkSession
        .builder()
        .config("spark.driver.extraJavaOptions", s"-Dderby.system.home=$testRoot/derby")
        .config(WAREHOUSE_PATH.key, s"$testRoot/spark-warehouse")
        .config(PARTITION_OVERWRITE_MODE.key, "dynamic")
        .config("spark.sql.sources.partitionColumnTypeInference.enabled", "false")
        .master("local[*]")

    implicit val spark: SparkSession = {
        deletePath(testRoot)
        configureSparkSession.getOrCreate()
    }

    private def deletePath(path: String): Boolean = Path(path).deleteRecursively()
}
